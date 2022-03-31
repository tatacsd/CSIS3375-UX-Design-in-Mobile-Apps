package com.example.datedemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.datedemo.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Dog> DogList = new ArrayList<>();
    //Note that buildFeatures viewBinding must be set to true for binding to work
    ActivityMainBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReadDogData(); // add anotation to work

        binding.recyclerViewDogItems
                .setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewDogItems
                .setAdapter(new DogAdapter(DogList));

    }

    @RequiresApi(api = Build.VERSION_CODES.O) // the annnotaion
    private void ReadDogData() {
        DogList = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.doginfo);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try{
            String CVSLine;

            while((CVSLine = bufferedReader.readLine()) != null) {
                String[] dogFields = CVSLine.split(",");
                int id = Integer.parseInt(dogFields[0]);
                String  picName = dogFields[1];
                String dogBreed = dogFields[2];
                String dogName = dogFields[3];
                String dobString = dogFields[4];

                // set the drawable picture
                int dogDrawable = getResources().getIdentifier(picName, "drawable", getPackageName());

                // formate the date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy"); // add the anotation
                LocalDate dob = LocalDate.parse(dobString, formatter);

                // Create the dog
                Dog eachDog = new Dog(id, dogBreed, dogName, dogDrawable, dob);
                DogList.add(eachDog);
           }

        } catch (Exception ex){
            Log.d("DATEDEMO", ex.getMessage());
        }

    }
}