package com.example.anle.gamepttuduy.Models;

import android.view.View;
import android.widget.ImageView;

import com.example.anle.gamepttuduy.iOnClick.ChonTuDung;

import java.lang.ref.WeakReference;

public class ODoanChu {

    private WeakReference<Object> CallBack;

    private ImageView img_odoan;
    private int position;

    public ODoanChu(ImageView img_odoan, int position, Object object) {
        this.img_odoan = img_odoan;
        this.position = position;
        this.CallBack=new WeakReference<>(object);
        this.img_odoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ChonTuDung callback=(ChonTuDung) CallBack.get();
                callback.chono(ODoanChu.this.position);
            }
        });
    }

    public ImageView getImg_odoan() {
        return img_odoan;
    }

    public void setImg_odoan(ImageView img_odoan) {
        this.img_odoan = img_odoan;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
