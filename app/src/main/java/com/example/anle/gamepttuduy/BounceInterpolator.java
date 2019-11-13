package com.example.anle.gamepttuduy;

import android.view.animation.Interpolator;

public class BounceInterpolator implements Interpolator {

    private double myAmplitude=1;
    private double myFrequency=10;

    public BounceInterpolator(double myAmplitude, double myFrequency) {
        this.myAmplitude = myAmplitude;
        this.myFrequency = myFrequency;
    }

    @Override
    public float getInterpolation(float input) {
        return (float)(-1*Math.pow(Math.E,-input/myAmplitude)*Math.cos(myFrequency*input)+1);
    }
}
