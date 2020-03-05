//package org.lightsys.kriolbiblewordfind
//
//import android.graphics.*
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.support.v4.view.MotionEventCompat.getActionMasked
//import android.widget.RelativeLayout
//import android.widget.ImageView
//import android.widget.Button
//import android.widget.TextView
//import android.view.MotionEvent
//import android.view.View
//import android.graphics.drawable.BitmapDrawable
//import android.widget.LinearLayout.LayoutParams
//import kotlinx.android.synthetic.main.activity_sample.*
//
//class sampleActivity : AppCompatActivity() {
//    var counter = 0
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sample)
//
//        //Create canvas and add it to layout
//        var myCanvas = DrawingView(this, null)
//        var params = LayoutParams(
//            LayoutParams.MATCH_PARENT,
//            LayoutParams.MATCH_PARENT)
//        addContentView(myCanvas, params)
//        myCanvas.bringToFront()
//
//        var rect = Rect()
//        image.getHitRect(rect)
//
//        for(i in 0..6){
//            for(j in 0..6){
//                image.setOnTouchListener { v: View, m: MotionEvent ->
//
//                rect.contains()
//            }
//        }
//    }
//
//
//
//
//    /*override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sample)
//
//        image.setOnTouchListener { v: View, m: MotionEvent ->
//            var startX = 0F
//            var startY = 0F
//            var endX = 0F
//            var endY = 0F
//
//            when (m.actionMasked) {
//                MotionEvent.ACTION_DOWN -> {
//                    startX = m.rawX
//                    startY = m.rawY
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    endX = m.rawX
//                    endY = m.rawY
//                }
//                MotionEvent.ACTION_UP -> {
//                    text.text = "$startX $startY $endX $endY"
//
//                    c.drawLine(200F, 200F, 356F, 653F, Paint())
//                }
//            }
//
//
//            //c.drawLine(m.x, m.y, )
//            true
//        }
//    }
//
//    private fun trackToTouch( v: View, m: MotionEvent)
//    {
//        val x = m.rawX
//        val y = m.rawY
//
//
//        when (m.actionMasked)
//        {
//            //MotionEvent.ACTION_DOWN -> text.text = "PRESS" //mark letter touched (add to an array)
//            //MotionEvent.ACTION_UP -> text.text = "RELEASE" //if x or y diff, create a letter from result
//            //MotionEvent.ACTION_MOVE -> text.text = "MOVE"  //track movement, if hit bound of another letter, add to array
//            //need a way to limit direction once started
//            //wait till next letter touched, determine from that by amount of change in x and y
//            //(if only y past tolerance, vert;
//            //if only x past tolerance, horiz;
//            //if both past tolerance, diag)
//            //(Also set direction by whether change in x/y are positive or negative)
//            //Dirs will be: left, right, down, up, diag_LD, diag_LU, diag_RD, diag_RU
//            MotionEvent.ACTION_MOVE -> {
//                button.text = "X: $x, Y: $y"
//
//                var coord = intArrayOf(0, 0)
//                val id = m.getPointerId(0)
//                v.getLocationOnScreen(coord)
//                text.text = "CoordX: " + coord[0] + " CoordY: " + coord[1]
//
//                v.x = x - coord[0]
//                v.y = y - coord[1]
//            }
//        }
//    }*/
//}