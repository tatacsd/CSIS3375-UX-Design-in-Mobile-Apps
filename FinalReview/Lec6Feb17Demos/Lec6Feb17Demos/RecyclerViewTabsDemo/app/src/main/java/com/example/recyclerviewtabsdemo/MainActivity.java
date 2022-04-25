package com.example.recyclerviewtabsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        /*TuneAdapter myTuneAdapter = new TuneAdapter(AllTunes);
        GridLayoutManager gm = new GridLayoutManager(this,2);
        recyclerViewTunes.setLayoutManager(gm);
        recyclerViewTunes.setAdapter(myTuneAdapter);*/
        TuneAdapter2 myTuneAdapter2 = new TuneAdapter2(AllTunes);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerViewTunes.setLayoutManager(lm);
        recyclerViewTunes.setAdapter(myTuneAdapter2);
        tuneTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this,"Clicked on tab index "
                        + tuneTabs.getSelectedTabPosition(),Toast.LENGTH_SHORT).show();
                //load relevant data
                switch(tuneTabs.getSelectedTabPosition()) {
                    case 0:
                        //myTuneAdapter.setTuneList(AllTunes);
                        //myTuneAdapter.notifyDataSetChanged(); //you could call notify dataset changed on the adapter object
                        myTuneAdapter2.setTuneList(AllTunes);
                        myTuneAdapter2.CurrentPlayInd = -1;
                        myTuneAdapter2.notifyDataSetChanged(); //either here or in the setter
                        break;
                    case 1:
                        //myTuneAdapter.setTuneList(MovieTunes);
                        myTuneAdapter2.setTuneList(MovieTunes);
                        myTuneAdapter2.setCurrentPlayInd(-1);
                        break;
                    case 2:
                        //myTuneAdapter.setTuneList(TVTunes);
                        myTuneAdapter2.setTuneList(TVTunes);
                        myTuneAdapter2.setCurrentPlayInd(-1);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {  }
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration
                                (MainActivity.this,lm.getOrientation()) {
            @Override
            public void getItemOffsets(Rect outRect, View view,
            RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 100;  outRect.top = 100;  outRect.right =100; outRect.bottom = 100;
            }
        };
        recyclerViewTunes.addItemDecoration(dividerItemDecoration);
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