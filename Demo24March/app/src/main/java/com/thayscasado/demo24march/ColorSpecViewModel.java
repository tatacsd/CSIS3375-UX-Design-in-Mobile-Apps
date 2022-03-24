package com.thayscasado.demo24march;

import android.os.Handler; // NEED TO BE THIS ONE

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class ColorSpecViewModel extends ViewModel {
    MutableLiveData<List<ColorSpec>> colorList = new MutableLiveData<>();

    // You just need a liveDta to return to the mutable
    public LiveData<List<ColorSpec>> getColors() {
        if(colorList == null){
            colorList = new MutableLiveData<>();
        }
        return colorList;
    }

    // use post and set to edit the list
    public void loadColors(List<ColorSpec> anyList){
        Handler handler = new Handler();
        // params: action and how much the delay
        handler.postDelayed(() ->{
            colorList.setValue(anyList);
        },1000);
    }
}
