/*
 * Copyright (c) 2016 Androidhacks7
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.androidhacks7.gifviewandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by androidhacks7 on 9/8/2016.
 */
public class GIFViewAndroid extends ImageView {

    private long startOfAnimation;

    private Movie movie;

    public GIFViewAndroid(Context context) {
        super(context);
    }

    public GIFViewAndroid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GIFViewAndroid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageResource(int resId) {
        InputStream inputStream = getResources().openRawResource(resId);
        movie = Movie.decodeStream(inputStream);
        if (movie == null) {
            super.setImageResource(resId);
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (movie == null) {
            super.onDraw(canvas);
            return;
        }

        if (startOfAnimation == 0) {
            startOfAnimation = System.currentTimeMillis();
        }

        if (movie != null) {
            int currentTime = (int) ((System.currentTimeMillis() - startOfAnimation) % movie.duration());
            movie.setTime(currentTime);
            float scaleX = (float) (this.getWidth() - getPaddingLeft() - getPaddingRight()) / movie.width();
            float scaleY = (float) (this.getHeight() - getPaddingTop() - getPaddingBottom()) / movie.height();
            canvas.scale(scaleX, scaleY);
            movie.draw(canvas, getPaddingLeft() * 2, getPaddingRight() * 2);
            invalidate();
        }
    }
}
