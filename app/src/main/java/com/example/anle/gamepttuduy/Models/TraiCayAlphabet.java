package com.example.anle.gamepttuduy.Models;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anle.gamepttuduy.iOnClick.ChonTuDung;

import java.lang.ref.WeakReference;

public class TraiCayAlphabet {

    private WeakReference<Object> Callback;

    private ImageView img;
    private char letter;
    private Context context;
    private int position;

    public TraiCayAlphabet(final Context context, ImageView img, char letter,Object object,int position) {
        this.context=context;
        this.img = img;
        this.letter = letter;
        this.Callback=new WeakReference<>(object);
        this.position=position;
        this.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ChonTuDung callback=(ChonTuDung)Callback.get();
                callback.chontu(TraiCayAlphabet.this.letter,TraiCayAlphabet.this.position);
            }
        });
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }


}
