package org.lightsys.kriolbiblewordfind;


import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

public class DrawingView extends View {

    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private int paintColor = Color.BLUE;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    private float startX;
    private float startY;
    private int startRow;
    private int startCol;


    public DrawingView(Context context, AttributeSet attrs){
        super(context, attrs);

        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAlpha(128);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(100F);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
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
                //startRow = 0;
                //startCol = 0;
                drawPath.moveTo(touchX, touchY);
                startX = touchX;
                startY = touchY;
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.rewind();
                drawPath.moveTo(startX, startY);
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.rewind();
                drawPath.moveTo(startX, startY);
                drawPath.lineTo(touchX, touchY);
                checkWord(startRow, startCol, 0, 0);
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

    public void checkWord(int row1, int col1, int row2, int col2) {
        if(false)//check if word in list
            paths.remove(paths.size()-1);
    }

}