package com.example.anle.gamepttuduy;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anle.gamepttuduy.BurstParticleView.BurstParticleSystem;
import com.example.anle.gamepttuduy.BurstParticleView.SampleTextureAtlasFactory;
import com.example.anle.gamepttuduy.CustomAdapter.GridViewTopicsAdapter;
import com.example.anle.gamepttuduy.CustomAdapter.RecyViewCauHoiAdapter;
import com.example.anle.gamepttuduy.CustomAdapter.RecyViewManChoiAdapter;
import com.example.anle.gamepttuduy.Models.CauHoi;
import com.example.anle.gamepttuduy.Models.ManChoi;
import com.example.anle.gamepttuduy.Models.ODoanChu;
import com.example.anle.gamepttuduy.Models.Topic;
import com.example.anle.gamepttuduy.Models.TraiCayAlphabet;
import com.example.anle.gamepttuduy.iOnClick.ChonTuDung;
import com.github.shchurov.particleview.ParticleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.PropertyPermission;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NhinHinhDoanChuActivity extends AppCompatActivity implements ChonTuDung,RecyViewManChoiAdapter.ChonMan,RecyViewCauHoiAdapter.ChonCauHoi {

    private static int diemcuaman;
    private static float diemtoida;
    private static int unLockScoreImg;

    private LinearLayout ln_topmenu;
    private String tuTA="red";
    private String tuSai="lqa";
    private String tuDoan=tuTA+tuSai;
    private List<TraiCayAlphabet> traiCayAlphabetList;
    private List<ODoanChu> oDoanChuList;
    private static Context context;
    private static List<int[]> arrTranslation;
    private FrameLayout frm_tree;
    private int solan=0;
    private CountDownTimer countDownTimer;
    private TextView tv_countDownTimer;
    private long timeLeftInMillis;
    private int kiemtrathua;
    private Dialog dialogYouLose;
    private Dialog dialogYouWin;
    private Dialog dialogMenu;
    private View viewDiaYouLose;
    private View viewDiaYouWin;
    private View viewDiaMenu;
    private LinearLayout ln_playagain,ln_homeback,ln_nextRound,ln_homebackw;
    private String kiemtradung;
    private char[] aletter;
    private int chonlaio=0;
    private FrameLayout fr_countDownTime;
    private Animation animBounce;
    private boolean khongchonnua;
    private boolean kiemtrachophepchonlai=false;
    private boolean xacnhandapan=false;
    private ProgressBar prg_unlockImg;
    private TextView tv_prgUnLock;
    private float savedPrgImg;
    private MediaPlayer loseSound,winSound,backgroundSound;
    private LinearLayout ln_reRound;
    private List<ImageView> listLuotChoi;
    private float scoretime;
    private int mrScoreTime;
    private float roundScore;
    private float buocnhay;
    private float heso;
    private List<ImageView> listImgScore;
    private LinearLayout ln_diaScore;
    private ParticleView particleViewBurst;
    private final BurstParticleSystem particleSystem=new BurstParticleSystem();
    private ImageView img_cancelYouWin;
    private RelativeLayout rel_menu;
    private List<Topic> topicList;
    private GridViewTopicsAdapter gridViewTopicsAdapter;
    private GridView grv_topics;
    private TextView tv_nametopics;
    private RecyclerView recy_chonman;
    private TextView tv_ttman;
    private RecyViewManChoiAdapter recyViewManChoiAdapter;
    private RecyclerView recy_cauhoi;
    private TextView tv_diemcau;
    private RecyViewCauHoiAdapter recyViewCauHoiAdapter;
    private TextToSpeech textToSpeech;
    private ImageView img_speechIcon;

    private List<CauHoi> chMan1List;
    private List<CauHoi> chMan2List;
    private List<ManChoi> manTp1List;
    private List<ManChoi> manTp2List;
    private List<ManChoi> manTp3List;
    private List<ManChoi> manTp4List;
    private int chonTPnao=0;
    private int chonMannao=0;
    private List<CauHoi> chManList;
    private List<ManChoi> manChoiList;

    private ImageView img_appleClickAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhin_hinh_doan_chu);
        initAll();
        addArrTranslation();
        setODapAn();
        showtraicay();
        bounceLetterBox(solan);
        startTimer();
        backgroundSound.start();
        initTextToSpeed();

        /*img_appleClickAnim=findViewById(R.id.img_appleClickAnim);
        Glide.with(this).load(R.drawable.sliceeff).error(R.drawable.aletter).into(img_appleClickAnim);*/
    }

    public void initAll(){
        diemcuaman=100;
        diemtoida=100f;
        unLockScoreImg=700;
        buocnhaycuadiem();
        context=getApplicationContext();
        ln_topmenu=findViewById(R.id.ln_dapan);
        ln_reRound=findViewById(R.id.ln_reRound);
        frm_tree=findViewById(R.id.frm_tree);
        rel_menu=findViewById(R.id.rel_menu);
        rel_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMenu.show();
            }
        });
        tv_countDownTimer=findViewById(R.id.tv_countDownTimer);
        fr_countDownTime=findViewById(R.id.fr_countDownTime);
        fr_countDownTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xacnhandapan){
                    countDownTimer.cancel();
                    tinhdiem();
                    kiemtraTrungKhop();
                    fr_countDownTime.clearAnimation();
                    xacnhandapan=false;
                }
            }
        });
        kiemtradung="";
        aletter=new char[tuTA.length()];
        setAnim();

        setUpDialogYouLose();
        setUpDialogYouWin();

        setUpDialogMenu();
        setUpGridViewTopics();
        setUpChonTopic();
        setUpRecyViewManChoi();
        setUpListViewCauHoi();

        setUpPrgUnlockImg();
        setUpMediaWL();

        setUpListLuotChoi();
        setShowMangChoi();
        roundScore=0f;
        scoretime=0f;
        kiemtrathua=0;
        setUpViewBurst();

    }

    public void initTextToSpeed(){
        img_speechIcon=new ImageView(this);
        img_speechIcon.setId(R.id.img_speechIcon);
        LinearLayout.LayoutParams lnLayoutParams=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
        lnLayoutParams.setMargins(3,0,3,0);
        img_speechIcon.setLayoutParams(lnLayoutParams);
        img_speechIcon.setImageResource(R.drawable.listeningicon);

        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }else {
                    Toast.makeText(NhinHinhDoanChuActivity.this,"ERROR TTS",Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_speechIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak(tuTA,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }

    public void setUpListViewCauHoi(){
        chMan1List=new ArrayList<>();
        chMan1List.add(new CauHoi(1,1,R.drawable.so0,0,"banana"));
        chMan1List.add(new CauHoi(2,1,R.drawable.so1,300,"doctor"));
        chMan1List.add(new CauHoi(3,1,R.drawable.so2,100,"apple"));
        chMan1List.add(new CauHoi(4,1,R.drawable.so3,0,"back"));
        chMan1List.add(new CauHoi(5,1,R.drawable.so4,0,"next"));
        chMan1List.add(new CauHoi(6,1,R.drawable.so5,0,"ahihi"));
        chMan1List.add(new CauHoi(7,1,R.drawable.so6,0,"ecec"));
        chMan1List.add(new CauHoi(8,1,R.drawable.so7,0,"ecec"));
        chMan1List.add(new CauHoi(9,1,R.drawable.so8,0,"ecec"));
        chMan1List.add(new CauHoi(10,1,R.drawable.so9,0,"ecec"));

        chMan2List=new ArrayList<>();
        chMan2List.add(new CauHoi(11,4,R.drawable.so9,0,"draw"));
        chMan2List.add(new CauHoi(12,4,R.drawable.so8,300,"to"));
        chMan2List.add(new CauHoi(13,4,R.drawable.so7,100,"move"));
        chMan2List.add(new CauHoi(14,4,R.drawable.so6,0,"hello"));
        chMan2List.add(new CauHoi(15,4,R.drawable.so5,0,"shit"));
        chMan2List.add(new CauHoi(16,4,R.drawable.so4,0,"one"));
        chMan2List.add(new CauHoi(17,4,R.drawable.so3,0,"two"));
        chMan2List.add(new CauHoi(18,4,R.drawable.so2,0,"three"));
        chMan2List.add(new CauHoi(19,4,R.drawable.so1,0,"six"));
        chMan2List.add(new CauHoi(20,4,R.drawable.so0,0,"ten"));

        recyViewCauHoiAdapter=new RecyViewCauHoiAdapter(this,chMan2List,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recy_cauhoi.setLayoutManager(linearLayoutManager);
        recy_cauhoi.setAdapter(recyViewCauHoiAdapter);
    }

    public void setUpGridViewTopics(){
        topicList=new ArrayList<>();
        topicList.add(new Topic(1,R.drawable.imgtopicschool,"School"));
        topicList.add(new Topic(2,R.drawable.imgtopicanimal,"Animal"));
        topicList.add(new Topic(3,R.drawable.imgtopicgiadinh,"Family"));
        topicList.add(new Topic(4,R.drawable.imgtopicfriend,"Friend"));
        gridViewTopicsAdapter=new GridViewTopicsAdapter(this,R.layout.gridviewtopics_layout,topicList);
        grv_topics.setAdapter(gridViewTopicsAdapter);
    }

    public void setHorizontalRecyViewManChoi(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recy_chonman.setLayoutManager(linearLayoutManager);
    }

    public void setUpRecyViewManChoi(){
        manTp1List=new ArrayList<>();
        manTp2List=new ArrayList<>();
        manTp3List=new ArrayList<>();
        manTp4List=new ArrayList<>();

        manTp1List.add(new ManChoi(1,1,R.drawable.so0,500,"5/10",1));
        manTp1List.add(new ManChoi(2,1,R.drawable.so1,700,"7/10",2));
        manTp1List.add(new ManChoi(3,1,R.drawable.so2,300,"3/10",3));

        manTp2List.add(new ManChoi(4,2,R.drawable.so3,400,"3/10",1));
        manTp2List.add(new ManChoi(5,2,R.drawable.so4,700,"7/10",2));
        manTp2List.add(new ManChoi(6,2,R.drawable.so5,200,"2/10",3));

        manTp3List.add(new ManChoi(7,3,R.drawable.so6,200,"2/10",1));
        manTp3List.add(new ManChoi(8,3,R.drawable.so7,200,"2/10",2));
        manTp3List.add(new ManChoi(9,3,R.drawable.so8,200,"2/10",3));

        manTp4List.add(new ManChoi(10,4,R.drawable.so9,200,"2/10",1));
        manTp4List.add(new ManChoi(11,4,R.drawable.so1,200,"2/10",2));
        manTp4List.add(new ManChoi(12,4,R.drawable.so2,200,"2/10",3));

        recyViewManChoiAdapter=new RecyViewManChoiAdapter(this,manTp1List,this);
        recy_chonman.setAdapter(recyViewManChoiAdapter);

        setHorizontalRecyViewManChoi();
    }

    @Override
    public void chonman(int position) {
        manChoiList=new ArrayList<>();
        switch (chonTPnao){
            case 1:manChoiList=manTp1List;break;
            case 2:manChoiList=manTp2List;break;
            default:Toast.makeText(NhinHinhDoanChuActivity.this,"Nothing",Toast.LENGTH_SHORT).show();break;
        }
        Toast.makeText(NhinHinhDoanChuActivity.this,"Màn: "+manChoiList.get(position).getIdManChoi(),Toast.LENGTH_SHORT).show();
        tv_ttman.setText(manChoiList.get(position).getTiendo()+"\n"+manChoiList.get(position).getDiemcuaman());
        switch(manChoiList.get(position).getIdManChoi()){
            case 1:chonMannao=1;
                    recyViewCauHoiAdapter.setCauHoiList(chMan1List);
                    recyViewCauHoiAdapter.notifyDataSetChanged();break;
            case 4:chonMannao=4;
                recyViewCauHoiAdapter.setCauHoiList(chMan2List);
                recyViewCauHoiAdapter.notifyDataSetChanged();break;
            default: Toast.makeText(NhinHinhDoanChuActivity.this,"Nothing",Toast.LENGTH_SHORT).show();break;
        }
    }

    @Override
    public void choncauhoi(int position) {
        chManList=new ArrayList<>();
        switch (chonMannao){
            case 1:chManList=chMan1List;break;
            case 4:chManList=chMan2List;break;
            default:Toast.makeText(NhinHinhDoanChuActivity.this,"Nothing",Toast.LENGTH_SHORT).show();break;
        }
        setUpNewGame(chManList.get(position).getTuta());
        dialogMenu.cancel();
        Toast.makeText(NhinHinhDoanChuActivity.this,"Câu: "+chManList.get(position).getIdCauHoi(),Toast.LENGTH_SHORT).show();
    }

    public void setUpNewGame(String tuTA){
        this.tuTA=tuTA;
        this.tuSai="ikl";
        this.tuDoan=this.tuTA+this.tuSai;
        solan=0;
        kiemtrachophepchonlai=false;
        chonlaio=0;
        khongchonnua=false;
        xacnhandapan=false;
        kiemtrathua=0;
        aletter=new char[this.tuTA.length()];
        roundScore=0f;
        scoretime=0f;
        setODapAn();
        showtraicay();
        bounceLetterBox(solan);
        countDownTimer.cancel();
        startTimer();
    }

    public void setUpChonTopic(){
        grv_topics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_nametopics.setText(topicList.get(position).getTopicName());
                switch (topicList.get(position).getIdTopic()){
                    case 1:recyViewManChoiAdapter.setManChoiList(manTp1List);chonTPnao=1;
                        recyViewManChoiAdapter.notifyDataSetChanged();break;
                    case 2:recyViewManChoiAdapter.setManChoiList(manTp2List);chonTPnao=2;
                        recyViewManChoiAdapter.notifyDataSetChanged();break;
                    case 3:recyViewManChoiAdapter.setManChoiList(manTp3List);chonTPnao=3;
                        recyViewManChoiAdapter.notifyDataSetChanged();break;
                    case 4:recyViewManChoiAdapter.setManChoiList(manTp4List);chonTPnao=4;
                        recyViewManChoiAdapter.notifyDataSetChanged();break;
                }
            }
        });
    }

    public void setUpViewBurst(){
        particleViewBurst.setTextureAtlasFactory(new SampleTextureAtlasFactory(getResources()));
        particleViewBurst.setParticleSystem(particleSystem);
        particleViewBurst.startRendering();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //particleViewBurst.startRendering();
    }

    @Override
    protected void onPause() {
        super.onPause();
        particleViewBurst.stopRendering();
    }

    public void setUplistImgScore(){
        String chuoiso=((int)roundScore)+"";
        listImgScore=new ArrayList<>();
        for (int k=0;k<chuoiso.length();k++){
            listImgScore.add(KiemTraSo(Integer.parseInt(chuoiso.charAt(k)+"")));
            ln_diaScore.addView(listImgScore.get(k));
        }
    }

    public void buocnhaycuadiem(){
        buocnhay=0;
        heso=0;
        switch ((int)diemtoida){
            case 100:
                buocnhay=0.38f;
                heso=1.67f;
                break;
            case 90:
                buocnhay=0.25f;
                heso=1.5f;
                break;
            case 80:
                buocnhay=0.1f;
                heso=1.33f;
                break;
            case 70:
                buocnhay=-0.027f;
                heso=1.17f;
                break;
        }
    }



    public void tinhdiem(){
        mrScoreTime=Math.round(scoretime);
        if (scoretime<=30){
            roundScore=diemtoida-(kiemtrathua*3);
        }else {
            roundScore=Math.round(diemtoida-((mrScoreTime-30)*heso))-(kiemtrathua*3);
            if (roundScore<=0){
                roundScore=1;
            }
        }
    }

    public void setUpListLuotChoi(){
        LinearLayout.LayoutParams lnParams=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
        lnParams.setMargins(4,0,4,0);
        listLuotChoi=new ArrayList<>();
        for (int k=0;k<3;k++){
            ImageView img=new ImageView(this);
            img.setImageResource(R.drawable.redheart);
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);
            img.setLayoutParams(lnParams);
            listLuotChoi.add(img);
        }
    }

    public void setShowMangChoi(){
        ln_reRound.setWeightSum(3);
        for (int k=0;k<listLuotChoi.size();k++){
            listLuotChoi.get(k).setVisibility(View.VISIBLE);
            ln_reRound.addView(listLuotChoi.get(k));
        }
    }

    public void setUpMediaWL(){
        winSound=MediaPlayer.create(getApplicationContext(),R.raw.amthanhthang);
        loseSound=MediaPlayer.create(getApplicationContext(),R.raw.amthanhthua);
        backgroundSound=MediaPlayer.create(getApplicationContext(),R.raw.nhacnengame);
        backgroundSound.setLooping(true);
    }

    public void setUpPlayAgain(){
        ImageView img=new ImageView(this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
        layoutParams.setMargins(0,3,0,3);
        img.setLayoutParams(layoutParams);
        img.setImageResource(R.drawable.replayicon);
        ln_reRound.setWeightSum(1);
        ln_reRound.addView(img);
        ln_reRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpViewForPlayAgain();
                ln_reRound.removeAllViews();
                setShowMangChoi();
                countDownTimer.cancel();
                scoretime=0;
                startTimer();
            }
        });
    }

    public void setUpViewForPlayAgain(){
        kiemtrachophepchonlai=false;
        chonlaio=0;
        kiemtrathua=0;
        xacnhandapan=false;
        solan=0;
        khongchonnua=false;
        ln_topmenu.removeAllViews();
        setODapAn();
        bounceLetterBox(0);
    }

    public void setUpDialogMenu(){
        dialogMenu=new Dialog(this);
        viewDiaMenu=LayoutInflater.from(this).inflate(R.layout.dialog_choose_round,(ViewGroup)findViewById(R.id.rel_diaMenu),false);
        dialogMenu.setContentView(viewDiaMenu);
        dialogMenu.setCanceledOnTouchOutside(true);
        dialogMenu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        grv_topics=viewDiaMenu.findViewById(R.id.grv_topics);
        recy_chonman=viewDiaMenu.findViewById(R.id.recy_chonman);
        recy_cauhoi=viewDiaMenu.findViewById(R.id.recy_cauhoi);
        tv_nametopics=viewDiaMenu.findViewById(R.id.tv_nametopics);
        tv_ttman=viewDiaMenu.findViewById(R.id.tv_ttman);
        tv_diemcau=viewDiaMenu.findViewById(R.id.tv_diemcau);
    }


    public void setUpDialogYouLose(){
        dialogYouLose=new Dialog(this);
        viewDiaYouLose=LayoutInflater.from(this).inflate(R.layout.dialog_gameover,(ViewGroup)findViewById(R.id.rel_gamelose),false);
        dialogYouLose.setContentView(viewDiaYouLose);
        dialogYouLose.setCanceledOnTouchOutside(true);
        dialogYouLose.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ln_playagain=viewDiaYouLose.findViewById(R.id.ln_playagain);
        ln_homeback=viewDiaYouLose.findViewById(R.id.ln_homeback);

        ln_playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpViewForPlayAgain();
                ln_reRound.removeAllViews();
                setShowMangChoi();
                countDownTimer.cancel();
                scoretime=0;
                startTimer();
                dialogYouLose.cancel();
            }
        });
    }

    public void setUpDialogYouWin(){
        dialogYouWin=new Dialog(this);
        viewDiaYouWin=LayoutInflater.from(this).inflate(R.layout.dialog_youwin,(ViewGroup)findViewById(R.id.rel_gameWin),false);
        dialogYouWin.setContentView(viewDiaYouWin);
        dialogYouWin.setCanceledOnTouchOutside(true);
        dialogYouWin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ln_nextRound=viewDiaYouWin.findViewById(R.id.ln_nextRound);
        ln_homebackw=viewDiaYouWin.findViewById(R.id.ln_homebackw);
        prg_unlockImg=viewDiaYouWin.findViewById(R.id.prg_unlockImg);
        ln_diaScore=viewDiaYouWin.findViewById(R.id.ln_diaScore);
        tv_prgUnLock=viewDiaYouWin.findViewById(R.id.tv_prgUnLock);
        particleViewBurst=viewDiaYouWin.findViewById(R.id.particleViewBurst);
        img_cancelYouWin=viewDiaYouWin.findViewById(R.id.img_cancelYouWin);

        ln_nextRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("NEXT","YEP");
            }
        });

        img_cancelYouWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogYouWin.cancel();
                setUpDialogYouWin();
                setUpViewBurst();
            }
        });
    }

    public void setUpPrgUnlockImg(){
        Resources res=getResources();
        Drawable drawable=res.getDrawable(R.drawable.hide_gradient);
        prg_unlockImg.setProgress(diemcuaman);
        prg_unlockImg.setSecondaryProgress(unLockScoreImg);
        prg_unlockImg.setMax(unLockScoreImg);
        prg_unlockImg.setProgressDrawable(drawable);
    }

    //Khởi tạo chạy thời gian với thời gian là 90s và mỗi 1s onTick 1 lần
    public void startTimer(){
        timeLeftInMillis=90000;
        countDownTimer=new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis=millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                kiemtraTrungKhop();
            }
        }.start();
    }

    public void updateCountDownText(){
        int minutes=(int) (timeLeftInMillis/1000)/60;
        int seconds=(int)(timeLeftInMillis/1000)%60;
        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        tv_countDownTimer.setText(timeLeftFormatted);
        scoretime=scoretime+1f+buocnhay;
        Log.d("TIME","count: "+Math.round(scoretime));
    }

    public void addArrTranslation(){
        arrTranslation=new ArrayList<>();
        int [] a1={dpToPx(70),dpToPx(20)};
        int [] a2={dpToPx(110),dpToPx(40)};
        int [] a3={dpToPx(150),dpToPx(20)};
        int [] a4={dpToPx(110),dpToPx(2)};
        int [] a5={dpToPx(65),dpToPx(55)};
        int [] a6={dpToPx(160),dpToPx(55)};
        int [] a7={dpToPx(80),dpToPx(89)};
        int [] a8={dpToPx(115),dpToPx(76)};
        int [] a9={dpToPx(153),dpToPx(90)};
        int [] a10={dpToPx(180),dpToPx(115)};
        int [] a11={dpToPx(50),dpToPx(123)};
        int [] a12={dpToPx(88),dpToPx(125)};
        int [] a13={dpToPx(125),dpToPx(112)};
        int [] a14={dpToPx(155),dpToPx(140)};
        int [] a15={dpToPx(190),dpToPx(155)};
        int [] a16={dpToPx(55),dpToPx(160)};
        int [] a17={dpToPx(96),dpToPx(165)};
        int [] a18={dpToPx(150),dpToPx(180)};
        int [] a19={dpToPx(180),dpToPx(200)};
        int [] a20={dpToPx(18),dpToPx(145)};
        int [] a21={dpToPx(25),dpToPx(195)};
        int [] a22={dpToPx(60),dpToPx(215)};
        int [] a23={dpToPx(110),dpToPx(200)};
        arrTranslation.add(a1);
        arrTranslation.add(a2);
        arrTranslation.add(a3);
        arrTranslation.add(a4);
        arrTranslation.add(a5);
        arrTranslation.add(a6);
        arrTranslation.add(a7);
        arrTranslation.add(a8);
        arrTranslation.add(a9);
        arrTranslation.add(a10);
        arrTranslation.add(a11);
        arrTranslation.add(a12);
        arrTranslation.add(a13);
        arrTranslation.add(a14);
        arrTranslation.add(a15);
        arrTranslation.add(a16);
        arrTranslation.add(a17);
        arrTranslation.add(a18);
        arrTranslation.add(a19);
        arrTranslation.add(a20);
        arrTranslation.add(a21);
        arrTranslation.add(a22);
        arrTranslation.add(a23);
    }

    public void setODapAn(){
        ln_topmenu.removeAllViews();
        oDoanChuList=new ArrayList<>();
        ln_topmenu.setWeightSum(tuTA.length());
        for (int k=0;k<tuTA.length();k++){
            ImageView img=new ImageView(this);
            img.setBackground(getDrawable(R.drawable.squareboxmain));
            LinearLayout.LayoutParams lnLayoutParams=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
            lnLayoutParams.setMargins(3,0,3,0);
            img.setLayoutParams(lnLayoutParams);
            oDoanChuList.add(new ODoanChu(img,k,this));
            ln_topmenu.addView(img);
        }
    }


    @Override
    public void chontu(char letter,int position) {
        if (chonlaio!=solan){
            aletter[chonlaio]=traiCayAlphabetList.get(position).getLetter();
            ganChuChoODoan(chonlaio,position);
            oDoanChuList.get(chonlaio).getImg_odoan().clearAnimation();
            chonlaio=solan;
            if (solan==tuTA.length()-1){
                khongchonnua=true;
                xacnhandapan=true;
                fr_countDownTime.startAnimation(animBounce);
            }else {
                kiemtrachophepchonlai=false;
            }
            if (!khongchonnua){
                bounceLetterBox(solan);
            }
        }else {
            if (!khongchonnua) {
                ganChuChoODoan(solan, position);
                chontu(solan, position);
            }
        }
    }

    public void ganChuChoODoan(int vitrio,int vitrichuchon){
        Bitmap bitmap=((BitmapDrawable)traiCayAlphabetList.get(vitrichuchon).getImg().getDrawable()).getBitmap();
        oDoanChuList.get(vitrio).getImg_odoan().setImageBitmap(bitmap);
    }

    @Override
    public void chono(int position) {
        if (position<solan||kiemtrachophepchonlai||(kiemtrachophepchonlai&&position==tuTA.length()-1)){
            oDoanChuList.get(chonlaio).getImg_odoan().clearAnimation();
            chonlaio=position;
            bounceLetterBox(position);
            khongchonnua=false;
            kiemtrachophepchonlai=true;
            if (position!=solan){
                oDoanChuList.get(solan).getImg_odoan().clearAnimation();
            }else {
                bounceLetterBox(position);
            }
        }
    }

    public void setAnim(){
        animBounce=AnimationUtils.loadAnimation(this,R.anim.bounce);
        BounceInterpolator interpolator=new BounceInterpolator(0.2,30);
        animBounce.setInterpolator(interpolator);
    }

    public void bounceLetterBox(int solan){
        oDoanChuList.get(solan).getImg_odoan().startAnimation(animBounce);
    }

    public void chontu(int boxIndex,int clickPosition){
        aletter[boxIndex] = traiCayAlphabetList.get(clickPosition).getLetter();
        oDoanChuList.get(solan).getImg_odoan().clearAnimation();
        if (solan < tuTA.length() - 1) {
            kiemtrachophepchonlai = false;
            solan++;
            chonlaio = solan;
            bounceLetterBox(solan);
        } else {
            kiemtrachophepchonlai = true;
            khongchonnua = true;
            xacnhandapan = true;
            fr_countDownTime.startAnimation(animBounce);
        }
    }

    public void kiemtraTrungKhop(){
        setUpPrgUnlockImg();
        StringBuilder cautraloi=new StringBuilder(aletter.length);
        for (Character a:aletter){
            cautraloi.append(a.charValue());
        }
        if (tuTA.equals(cautraloi.toString())){
            diemcuaman+=roundScore;
            tv_prgUnLock.setText(diemcuaman+"/"+unLockScoreImg);
            setUplistImgScore();
            try {
                particleSystem.addBurst(526.5125f,159.88391f);
                particleSystem.addBurst(539.5005f,821.5393f);
                dialogYouWin.show();
            }catch (Exception e){
                Log.d("Exc","Error: "+e.getMessage());
            }
            winSound.start();
            ln_topmenu.setWeightSum(tuTA.length()+1);
            ln_topmenu.addView(img_speechIcon);
        }else {
            kiemtrathua();
            loseSound.start();
        }
    }

    public void kiemtrathua(){
        kiemtrathua++;
        switch (kiemtrathua){
            case 1: tieuhaoMang(0); break;
            case 2: tieuhaoMang(1); break;
            case 3: tieuhaoMang(2); break;
        }
        if (kiemtrathua==3){
            setUpDialogYouLose();
            setUpPlayAgain();
            dialogYouLose.show();
        }
    }

    public void tieuhaoMang(int p){
        listLuotChoi.get(p).setVisibility(View.GONE);
        countDownTimer.cancel();
        startTimer();
    }

    public void showtraicay(){
        frm_tree.removeAllViews();
        traiCayAlphabetList=new ArrayList<>();
        for (int k=0;k<tuDoan.length();k++){
            ImageView img=new ImageView(this);
            img.setBackground(getDrawable(R.drawable.watercolorapplered));
            img.setLayoutParams(new FrameLayout.LayoutParams(dpToPx(30),dpToPx(30)));
            traiCayAlphabetList.add(new TraiCayAlphabet(this,img,tuDoan.charAt(k),this,k));
            LetterCheck(k,tuDoan.charAt(k));
        }
        setAppleTranslation(tuDoan);
    }

    public static int dpToPx(int dp) {
        float density = context.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }

    public void setAppleTranslation(String tusai){
        Random rd=new Random();
        boolean check;
        int[] arrOldNum=new int[23];
        int rdNum=rd.nextInt(23);
        for (int k=0;k<tusai.length();k++){
            check=false;
            arrOldNum[k]=rdNum;
            frm_tree.addView(traiCayAlphabetList.get(k).getImg());
            traiCayAlphabetList.get(k).getImg().setTranslationX(arrTranslation.get(rdNum)[0]);
            traiCayAlphabetList.get(k).getImg().setTranslationY(arrTranslation.get(rdNum)[1]);
            while (!check){
                check=true;
                rdNum=rd.nextInt(23);
                for (int i=0;i<arrOldNum.length;i++){
                    if (arrOldNum[i]==rdNum){
                        check=false;
                    }
                }
            }
        }
    }

    public void LetterCheck(int position, char letter){
        switch (letter){
            case 'a': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.aletter);break;
            case 'b': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.bletter);break;
            case 'c': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.cletter);break;
            case 'd': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.dletter);break;
            case 'e': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.eletter);break;
            case 'f': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.fletter);break;
            case 'g': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.gletter);break;
            case 'h': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.hletter);break;
            case 'i': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.iletter);break;
            case 'j': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.jletter);break;
            case 'k': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.kletter);break;
            case 'l': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.lletter);break;
            case 'm': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.mletter);break;
            case 'n': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.nletter);break;
            case 'o': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.oletter);break;
            case 'p': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.pletter);break;
            case 'q': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.xqletter);break;
            case 'r': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.rletter);break;
            case 's': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.sletter);break;
            case 't': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.tletter);break;
            case 'u': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.uletter);break;
            case 'v': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.vletter);break;
            case 'w': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.wletter);break;
            case 'x': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.xletter);break;
            case 'y': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.yletter);break;
            case 'z': traiCayAlphabetList.get(position).getImg().setImageResource(R.drawable.zletter);break;
        }
    }

    public ImageView KiemTraSo(int so){
        ImageView img=new ImageView(this);
        LinearLayout.LayoutParams lnParams=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
        lnParams.setMarginEnd(3);
        img.setLayoutParams(lnParams);
        switch (so){
            case 0:img.setImageResource(R.drawable.so0);break;
            case 1:img.setImageResource(R.drawable.so1);break;
            case 2:img.setImageResource(R.drawable.so2);break;
            case 3:img.setImageResource(R.drawable.so3);break;
            case 4:img.setImageResource(R.drawable.so4);break;
            case 5:img.setImageResource(R.drawable.so5);break;
            case 6:img.setImageResource(R.drawable.so6);break;
            case 7:img.setImageResource(R.drawable.so7);break;
            case 8:img.setImageResource(R.drawable.so8);break;
            case 9:img.setImageResource(R.drawable.so9);break;
        }
        return img;
    }
}
