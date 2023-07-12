package com.example.livedrawing;

import android.graphics.PointF;

public class Particle {
    PointF mVelocity;
    PointF mPosition;
    Particle(PointF directory){
        mVelocity = new PointF();
        mPosition = new PointF();
        mVelocity.x = directory.x;
        mVelocity.y = directory.y;
    }
    void update(float fps){
        mPosition.x+=mVelocity.x;
        mPosition.y+=mVelocity.y;
    }
    void setPosition(PointF position){
        mPosition.x=position.x;
        mPosition.y=position.y;
    }
    PointF getPosition(){
        return mPosition;
    }
}
