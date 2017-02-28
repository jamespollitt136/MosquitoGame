package com.example.james.science;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by James on 22/11/2016.
 */

public class MosquitoGameObject {

    protected float x;
    protected float y;
    protected float dx;
    protected float dy;
    protected Drawable mosquitoImage;
    //Rectangle r;

    public MosquitoGameObject(float x, float y, float dx, float dy, Drawable mosquitoImage) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.mosquitoImage = mosquitoImage;

    }

    public void move(Canvas canvas) {
        x += dx;
        y += dy;
        if (x > canvas.getWidth() || x < 0)
            dx = -dx;
        if (y > canvas.getHeight() || y < 0)
            dy = -dy;
        mosquitoImage.setBounds((int) x, (int) y, (int) (x + 200f), (int) (y + 200f));
        mosquitoImage.draw(canvas);

    }

}