package com.example.livedrawing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

public class ParticleSystem {
    private float mDuration;

    private ArrayList<Particle> mParticle;
    private Random random = new Random();
    boolean mIsRunning = false;

    void init(int numParticles){
        mParticle = new ArrayList<>();
        for(int i=0; i<numParticles; i++){
            float angle = (random.nextInt(360));
            angle = angle * 3.15f/180.f;
            float speed = (random.nextInt(10)+1);
            PointF direction;
            direction = new PointF((float) Math.cos(angle)*speed,(float) Math.sin(angle)*speed);
            mParticle.add(new Particle(direction));
        }
    }

    void update(long fps){
        mDuration -=(1f/fps);
        for(Particle p : mParticle){
            p.update(fps);
        }
        if(mDuration<0){
            mIsRunning = false;
        }
    }

    void emitParticles(PointF startPosition){
        mIsRunning = true;
        mDuration = 3f;
        for(Particle p : mParticle){
            p.setPosition(startPosition);
        }
    }

    void draw(Canvas canvas, Paint paint){
        for(Particle p: mParticle){
            paint.setColor(Color.argb(255,255,255,255));
            float sizeX =0;
            float sizeY =0;
            sizeX = 10;
            sizeY=10;
            canvas.drawCircle(p.getPosition().x,p.getPosition().y,sizeX,paint);

        }
    }
}
