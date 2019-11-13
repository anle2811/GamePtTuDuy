package com.example.anle.gamepttuduy.CustomAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anle.gamepttuduy.Models.CauHoi;
import com.example.anle.gamepttuduy.Models.ManChoi;
import com.example.anle.gamepttuduy.R;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyViewCauHoiAdapter extends RecyclerView.Adapter<RecyViewCauHoiAdapter.CauHoiViewHolder> {

    @Override
    public CauHoiViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.listviewcauhoi_layout,viewGroup,false);
        return new CauHoiViewHolder(view);
    }

    public interface ChonCauHoi{
        void choncauhoi(int position);
    }

    @Override
    public void onBindViewHolder( CauHoiViewHolder cauHoiViewHolder, int i) {
        CauHoi cauHoi=cauHoiList.get(i);
        cauHoiViewHolder.tv_soCau.setText((i+1)+"");
        cauHoiViewHolder.img_anhCauhoi.setImageResource(cauHoi.getImgResource());
        cauHoiViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ChonCauHoi chonCauHoi=(ChonCauHoi)callBack.get();
                chonCauHoi.choncauhoi(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cauHoiList.size();
    }

    private Context context;
    private List<CauHoi> cauHoiList;
    private WeakReference<Object> callBack;

    public RecyViewCauHoiAdapter(Context context,List<CauHoi> cauHoiList,Object object){
        this.context=context;
        this.cauHoiList=cauHoiList;
        callBack=new WeakReference<>(object);
    }

    public void setCauHoiList(List<CauHoi> cauHoiList) {
        this.cauHoiList = cauHoiList;
    }

    public class CauHoiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img_anhCauhoi;
        private TextView tv_soCau;
        private ItemClickListener itemClickListener;

        public CauHoiViewHolder(View viewItem){
            super(viewItem);
            img_anhCauhoi=viewItem.findViewById(R.id.img_anhCauHoi);
            tv_soCau=viewItem.findViewById(R.id.tv_soCau);
            viewItem.setOnClickListener(this);
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
