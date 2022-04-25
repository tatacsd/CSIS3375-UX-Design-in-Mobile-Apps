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
        ReadDogData();

        binding.recyclerViewDogItems
                .setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewDogItems
                .setAdapter(new DogAdapter(DogList, new DogAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int i) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-d-yyyy");
                        String dobStr = formatter.format(DogList.get(i).getDob());

                        //you can also get the other details
                        String dogName = DogList.get(i).getDogName();
                        String dogBreed = DogList.get(i).getDogBreed();


                        binding.txtViewAdoptionSumary.setText("Adopted Dog: " + dogName
                                + "\nBreed Name: " + dogBreed
                                + "\nDob: " + dobStr);
                    }
                }));

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ReadDogData() {
        DogList = new ArrayList<>();

        InputStream inputStream
                = getResources().openRawResource(R.raw.doginfo);
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String CSVLine; //just to maintain each line that is read
            //if header is in the data, need to read it out...
           //CSVLine = reader.readLine(); //incase there is header

            while((CSVLine = reader.readLine()) != null){
                String[] dogFields = CSVLine.split(",");
                int id = Integer.parseInt(dogFields[0]);
                String picName = dogFields[1];
                String dogBreed = dogFields[2];
                String dogName = dogFields[3];
                String dobStr = dogFields[4];

                int dogDrawable = getResources()
                        .getIdentifier(picName,
                                "drawable",getPackageName());
                //d- day of the month, M - month, y - year
                //dd - two digit for date, d- one or more digits for date
                //MM - two digit month number
                //MMM - three digit letter for month
                //yy - two digit year
                //yyyy - four digit year
                
                DateTimeFormatter formatter
                        = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                LocalDate dob = LocalDate.parse(dobStr,formatter);

                Dog eachDog = new Dog(id,dogBreed,dogName,dogDrawable,dob);
                DogList.add(eachDog);
            }

        } catch (Exception ex) {
            Log.d("DATEDEMO", ex.getMessage());
        }
    }
}