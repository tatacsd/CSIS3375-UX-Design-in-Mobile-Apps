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
        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
             return true; //onDown needs to return true for all other gestures to be detected
            // return super.onDown(e); //by default base class return false
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("GESTUREDEMO","Single Tap Detected....");
            onSingleClick();
            return super.onSingleTapConfirmed(e);
        }
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
