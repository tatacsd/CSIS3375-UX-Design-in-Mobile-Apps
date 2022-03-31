package com.example.viewdemo;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;

public class CustomTouchListener implements View.OnTouchListener{
    Context context;
    GestureDetectorCompat gestureDetectorCompat;

    //need a constructor for CustomTouchListener
    public CustomTouchListener(Context context) {
        this.context = context;
        gestureDetectorCompat = new GestureDetectorCompat(context,
                                            new CustomGestureListener());
    }

    //need this CustomGestureListener to create the gestureDetectorCompat
    public class CustomGestureListener
            extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_DIST_THRESHOLD = 10;
        private static final int SWIPE_VEL_THRESHOLD = 50;

        @Override
        public void onLongPress(MotionEvent e) {

            super.onLongPress(e);
            onLongClick();
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distX = e2.getX() - e1.getX();
            float distY = e2.getY() - e1.getY();

            Log.d("GESTUREDEMO","Entered on fling.. distX, distY, velX, velY: "
                                            + distX + distY + velocityX + velocityY);
            if (Math.abs(distX) > Math.abs(distY) &&
                Math.abs(distX) > SWIPE_DIST_THRESHOLD &&
                Math.abs(velocityX) > SWIPE_VEL_THRESHOLD){
                //horizontal swipe has occurred
                if (distX > 0) {
                    //right swipe
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
            } else if(Math.abs(distY) > Math.abs(distX) &&
                        Math.abs(distY) > SWIPE_DIST_THRESHOLD &&
                            Math.abs(velocityY) > SWIPE_VEL_THRESHOLD){
                //vertical swipe has occurred
                if (distY > 0) {
                    onSwipeDown();
                } else {
                    onSwipeUp();
                }
            }

            return super.onFling(e1, e2, velocityX, velocityY);

        }

        @Override
        public boolean onDown(MotionEvent e) {
             return true; //onDown needs to return true for all other gestures to be detected
            // return super.onDown(e); //by default base class return false
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("GESTUREDEMO","Double Tap Detected in the custom touch listener...");
            onDoubleClick();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("GESTUREDEMO","Single Tap Detected....");
            onSingleClick();
            return super.onSingleTapConfirmed(e);
        }
    }

    public void onLongClick() {
        Log.d("GESTUREDEMO","Long Press Detected in the custom touch listener...");

    }

    public void onSwipeUp(){
        //you may add logs as you need
        //but can be overridden in the main activity
        Log.d("GESTUREDEMO","Swipe up Detected in the custom touch listener...");
    }

    public void onSwipeDown(){
        Log.d("GESTUREDEMO","Swipe down Detected in the custom touch listener...");

    }
    public void onSwipeRight() {
        Log.d("GESTUREDEMO","Swipe right Detected in the custom touch listener...");

    }

    public void onSwipeLeft() {
        Log.d("GESTUREDEMO","Swipe left Detected in the custom touch listener...");
    }

    public void onDoubleClick() {
        Log.d("GESTUREDEMO","Double click inside the custom touch listener..");
    }

    public void onSingleClick() {
        Log.d("GESTUREDEMO","Detected Single Click inside Custom Touch Listener");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //return false; //need to not return false
        return gestureDetectorCompat.onTouchEvent(motionEvent);
    }
}
