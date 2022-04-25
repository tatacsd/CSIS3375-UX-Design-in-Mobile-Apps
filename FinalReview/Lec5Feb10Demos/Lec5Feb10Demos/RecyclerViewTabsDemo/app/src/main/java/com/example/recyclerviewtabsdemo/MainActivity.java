package com.example.recyclerviewtabsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerviewtabsdemo.model.Tune;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> TuneNames = new ArrayList<>(
            Arrays.asList("Beauty and The Beast","Lion King",
                    "Mary Poppins","Game of Thrones","Ozark"));
    List<Integer> TunePics = new ArrayList<>(Arrays.asList(R.drawable.beauty,
                            R.drawable.lionking,R.drawable.marypoppins,
                            R.drawable.gameofthrones,R.drawable.ozark ));
    List<Tune> AllTunes = new ArrayList<>();
    List<Tune> MovieTunes = new ArrayList<>();
    List<Tune> TVTunes = new ArrayList<>();
    final String TAG = "RECYCLERVIEWDEMO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadModelData();

        RecyclerView recyclerViewTunes = findViewById(R.id.reccyclerViewTunes);
        TabLayout tuneTabs = findViewById(R.id.tabLayoutTunes);

        tuneTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void LoadModelData(){
        for (int i = 0; i < TuneNames.size(); i++){
            Tune eachTune = new Tune(TuneNames.get(i),TunePics.get(i));
            AllTunes.add(eachTune);
        }
        MovieTunes = AllTunes.subList(0,3); //sublist has exclusive upper bound
        TVTunes = AllTunes.subList(3,5);
    }
}