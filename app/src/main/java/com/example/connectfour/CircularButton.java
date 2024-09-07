package com.example.connectfour; //This lass is for circular buttons in the grid 

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;

public class CircularButton extends AppCompatButton {
    private Paint paint;

    public CircularButton(Context context) {
        super(context);
        init();
    }

    public CircularButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackground(null);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(android.R.color.darker_gray)); // Set your color
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height);

        // Draw circular button
        RectF rectF = new RectF(0, 0, diameter, diameter);
        canvas.drawOval(rectF, paint);

        // Draw the text on top of the circle
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width, width); // Make height equal to width to maintain circle shape
    }
}
