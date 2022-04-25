package com.thayscasado.doginstagram;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class DogViewModel extends ViewModel {
    MutableLiveData<List<Dog>> mutableLiveData = new MutableLiveData<>();
    public LiveData<List<Dog>> getDogs() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            this.notifyAll();
        }
        return mutableLiveData;
    }
    public void loadDogs(List<Dog> anyList) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            mutableLiveData.setValue(anyList);
            }, 1000);
    }
}
