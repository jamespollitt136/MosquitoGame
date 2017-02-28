package com.example.james.science;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by James on 22/11/2016.
 */

public class MosquitoSurfaceView extends SurfaceView  implements Runnable{

    private SurfaceHolder surfaceHolder;
    private Thread mosquitoGameThread;
    private int score;
    private boolean isRunning = true;
    private Paint paint;
    private ArrayList<MosquitoGameObject> mosquitoObjects = new ArrayList<MosquitoGameObject>();
    private MosquitoGameObject[] mosquitoGameObjectArray = new MosquitoGameObject[2];
    private int i = 0;

    OnTouchListener arrayListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mosquitoGameObjectArray[i] = null;
            mosquitoObjects.remove(i);
            score = score + 10;
            Toast.makeText(v.getContext(), "SWAT!", Toast.LENGTH_SHORT).show();
            if(i == 1) {
                stop();
                mosquitoObjects.remove(1);
            }
            i = i + 1;
            checkArrayEmpty(v.getContext());
            return false;
        }
    };

    public MosquitoSurfaceView(Context context) {
        super(context);

        this.score = 40;

        paint = new Paint();
        paint.setColor(Color.WHITE);

        surfaceHolder = getHolder();

        mosquitoGameThread = new Thread(this);
        mosquitoGameThread.start();

        MosquitoGameObject mosquito1 = new MosquitoGameObject(100, 100, 10, 10,
                ContextCompat.getDrawable(context, R.drawable.mosquito_game)); //set the mosquito image
        mosquitoObjects.add(mosquito1);
        MosquitoGameObject mosquito2 = new MosquitoGameObject(200, 100, 20, 10,
                ContextCompat.getDrawable(context, R.drawable.mosquito_two));
        mosquitoObjects.add(mosquito2);

        mosquitoGameObjectArray[0] = mosquito1;
        mosquitoGameObjectArray[1] = mosquito2;

        this.setOnTouchListener(arrayListener);
    }

    @Override
    public void run() {
        while(isRunning) {
            if(!surfaceHolder.getSurface().isValid())
                continue;
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
            for(MosquitoGameObject mosquitoGameObject : mosquitoObjects) {
                mosquitoGameObject.move(canvas);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void stop() {
        isRunning = false;
        while(true) {
            try {
                mosquitoGameThread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void start() {
        isRunning = true;
        mosquitoGameThread = new Thread(this);
        mosquitoGameThread.start();
    }

    public void checkArrayEmpty(Context context) {
        if(mosquitoGameObjectArray.length == 0) {
            Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show();
            //stop();
        }
    }
}