package com.example.anle.gamepttuduy.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anle.gamepttuduy.Models.ManChoi;
import com.example.anle.gamepttuduy.R;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyViewManChoiAdapter extends RecyclerView.Adapter<RecyViewManChoiAdapter.ManChoiViewHolder> {

    private Context context;
    private List<ManChoi> manChoiList;
    private WeakReference<Object> callBack;


    public RecyViewManChoiAdapter(Context context, List<ManChoi> manChoiList, Object object) {
        this.context = context;
        this.manChoiList = manChoiList;
        callBack=new WeakReference<>(object);
    }

    public interface ChonMan{
        void chonman(int position);
    }

    public void setManChoiList(List<ManChoi> manChoiList) {
        this.manChoiList = manChoiList;
    }


    @Override
    public ManChoiViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.layout_recyviewchonman,viewGroup,false);
        return new ManChoiViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ManChoiViewHolder manChoiViewHolder, int i) {
        ManChoi manChoi=manChoiList.get(i);
        manChoiViewHolder.img_anhman.setImageResource(manChoi.getImgResource());
        manChoiViewHolder.tv_lvmanchoi.setText("LV: "+manChoi.getCapdo());
        manChoiViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ChonMan chonMan=(ChonMan)callBack.get();
                chonMan.chonman(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return manChoiList.size();
    }

    public class ManChoiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView img_anhman;
        private TextView tv_lvmanchoi;
        private ItemClickListener itemClickListener;

        public ManChoiViewHolder(View itemView) {
            super(itemView);
            img_anhman=itemView.findViewById(R.id.img_anhman);
            tv_lvmanchoi=itemView.findViewById(R.id.tv_lvmanchoi);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }
    }
}
