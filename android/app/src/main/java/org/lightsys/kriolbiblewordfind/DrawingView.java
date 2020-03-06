package org.lightsys.kriolbiblewordfind;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DrawingView extends View {

    private puzzleActivity activity;
    private Path drawPath;
    private Paint drawPaint;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private int paintColor;
    private Rect rect;
    private TextView text;
    private float startX;
    private float startY;
    private float endX;
    private float endY;


    public DrawingView(Context context, AttributeSet attrs, puzzleActivity activity){
        super(context, attrs);
        this.activity = activity;
        rect = new Rect();
        paintColor = getResources().getColor(R.color.colorHomeButton);

        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAlpha(128);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(100F);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    //setup drawing
    private void initPath(){

        //generate new path
        drawPath = new Path();
        paths.add(drawPath);

    }

    //draw the view - will be called after touch event
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Path p : paths) {
            canvas.drawPath(p, drawPaint);
        }
    }

    //register user touches as drawing action
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        //respond to down, move and up events
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initPath();
                activity.getGridCell(touchX, touchY).getHitRect(rect);
                startX = rect.exactCenterX();
                startY = rect.exactCenterY();
                drawPath.moveTo(startX, startY);
                break;
            case MotionEvent.ACTION_MOVE:
                this.getHitRect(rect);
                drawPath.rewind();
                drawPath.moveTo(startX, startY);
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.rewind();
                drawPath.moveTo(startX, startY);
                activity.getGridCell(touchX, touchY).getHitRect(rect);
                endX = rect.exactCenterX();
                endY = rect.exactCenterY();
                drawPath.lineTo(endX, endY);
                //TODO: This
                if(activity.isValidWord(startX, startY, endX, endY)){
                    //
                } else {
                    paths.remove(paths.size()-1);
                }
                break;
            default:
                return false;
        }

        //redraw
        invalidate();
        return true;
    }

    //update color
    public void setColor(String newColor){
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }
}