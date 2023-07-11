package com.example.livedrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LiveDrawingView extends SurfaceView implements Runnable{
    private final boolean DEBUGGING = true;
    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    private Paint mPaint;
    private long mFPS;
    private final int MILLIS_IN_SECOND = 1000;
    private int mScreenX;
    private int mScreenY;
    private int mFrontSize;
    private int mFrontMargin;
    private Thread mThread = null;
    private volatile boolean mDrawing;
    private boolean mPause = true;

    public LiveDrawingView(Context context, int x, int y){
        super(context);
        this.mScreenX=x;
        this.mScreenY=y;
        mFrontSize = mScreenX/20;
        mFrontMargin=mScreenY/75;
        mOurHolder = getHolder();
        mPaint = new Paint();

    }
    public void run(){
        while (mDrawing){
            long frameStartTime = System.currentTimeMillis();
            if(!mPause){
                update();
            }
            draw();
            long timeThisFrame = System.currentTimeMillis()-frameStartTime;
            if(timeThisFrame>0){
                mFPS = MILLIS_IN_SECOND/timeThisFrame;
            }
        }
    }
    private void update(){

    }

    private void draw(){
        if(mOurHolder.getSurface().isValid()){
            mCanvas= mOurHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255,0,0,0));
            mPaint.setColor(Color.argb(255,255,255,255));
            mPaint.setTextSize(mFrontSize);

            if(DEBUGGING){
                printDebuggingText();
            }
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    private void printDebuggingText(){
        int debugSize = mFrontSize /2;
        int debugStart =150;
        mPaint.setTextSize(debugSize);
        mCanvas.drawText("FPS "+mFPS,10, debugStart+debugSize,mPaint);
    }
    public void pause(){
        mDrawing = false;
        try {
            mThread.join();
        }catch (InterruptedException e){
            Log.e("Error","joining thread");
        }

    }

    public void resume(){
        mDrawing=true;
        mThread = new Thread(this);
        mThread.start();
    }
}
