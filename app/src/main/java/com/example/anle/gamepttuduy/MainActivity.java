package com.example.anle.gamepttuduy;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    private ImageView img_iconHinhTron,img_iconHinhThoi,img_iconTamGiac;
    private Animation anim_Move;
    private Animation anim_Move1;
    private Animation anim_Move2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();
        anim_Move=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        anim_Move1=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move1);
        anim_Move2=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move2);
        anim_Move.setAnimationListener(this);
        img_iconHinhTron.startAnimation(anim_Move);
        img_iconHinhThoi.startAnimation(anim_Move1);
        img_iconTamGiac.startAnimation(anim_Move2);
    }

    public void map(){
        img_iconHinhTron=findViewById(R.id.img_iconHinhTron);
        img_iconHinhThoi=findViewById(R.id.img_iconHinhThoi);
        img_iconTamGiac=findViewById(R.id.img_iconTamGiac);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
