package com.example.viewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView txtViewWelcomeMsg; //you cannot call findViewById until setContentView()
    boolean bigger = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d("GESTUREDEMO","Starting Gesture Demo..."); //just a log test

        txtViewWelcomeMsg = findViewById(R.id.txtViewWelcomeMsg);

        ImageView imgViewSample = findViewById(R.id.imgViewSample);

        Drawable img = ResourcesCompat.getDrawable(getResources(),
                                    R.drawable.border,getTheme());

        Objects.requireNonNull(img); //one way to check if img requiring it to not be null
        img.setBounds(0, 0, img.getIntrinsicWidth(),img.getIntrinsicHeight());

        txtViewWelcomeMsg.setCompoundDrawables(img,null,img,null);
        txtViewWelcomeMsg.setCompoundDrawablePadding(8);
        txtViewWelcomeMsg.setAlpha(1.0f); //1 means opacity is full - fully opaque

        Button btnShowTextOrImage = findViewById(R.id.btnShowTextOrImage);
        Button btnShowBoth = findViewById(R.id.btnShowBoth);

        btnShowBoth.setOnClickListener((View view) -> {
            txtViewWelcomeMsg.setVisibility(View.VISIBLE);
            imgViewSample.setVisibility(View.VISIBLE);
        });

        btnShowTextOrImage.setOnClickListener((View view) -> {
            if (btnShowTextOrImage.getText().equals("Show Text")){
                txtViewWelcomeMsg.setVisibility(View.VISIBLE);
                imgViewSample.setVisibility(View.INVISIBLE); //try both INVISIBLE and GONE
                btnShowTextOrImage.setText(getResources().getText(R.string.txtShowImage));
            } else {
                imgViewSample.setVisibility(View.VISIBLE);
                txtViewWelcomeMsg.setVisibility(View.GONE); //try both INVISIBLE AND GONE and keep it consistent
                btnShowTextOrImage.setText(getResources().getText(R.string.txtShowText));
            }

        });

        txtViewWelcomeMsg.setOnTouchListener(
                new CustomTouchListener(MainActivity.this) {
                    @Override
                    public void onDoubleClick() {
                        super.onDoubleClick();
                        try {
                            if (!bigger) {
                                txtViewWelcomeMsg.setTextSize(
                                        txtViewWelcomeMsg.getTextSize() / getResources().getDisplayMetrics().density + 10);
                                bigger = true;
                            } else {
                                txtViewWelcomeMsg.setTextSize(
                                        txtViewWelcomeMsg.getTextSize() / getResources().getDisplayMetrics().density - 10);
                                bigger = false;
                            }
                        } catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onSingleClick() {
                        super.onSingleClick();
                        Log.d("GESUREDEMO","Detected Single Click on TextView");
                        Toast.makeText(MainActivity.this, "Clicked on TextView", Toast.LENGTH_SHORT).show();
                        if (txtViewWelcomeMsg.getCurrentTextColor() !=
                            ResourcesCompat.getColor(getResources(),R.color.teal_200,getTheme())){
                            txtViewWelcomeMsg.setTextColor(
                                    ResourcesCompat.getColor(getResources(),R.color.teal_200,getTheme()));
                        } else {
                            txtViewWelcomeMsg.setTextColor(Color.rgb(255,255,255));
                        }
                    }

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        Log.d("GESTUREDEMO","Detected OnTouch on TextView");
                        return super.onTouch(view, motionEvent);
                    }
                });

        //Take a look at the custom touch listener added to the image view as well
        imgViewSample.setOnTouchListener(new CustomTouchListener(MainActivity.this){
            @Override
            public void onSingleClick() {
                super.onSingleClick();
                Log.d("GESUREDEMO","Detected Single Click on ImageView");
                Toast.makeText(MainActivity.this, "Clicked on ImageView", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("GESTUREDEMO","Detected OnTouch on ImageView");
                return super.onTouch(view, motionEvent);
            }
        });

    }
}