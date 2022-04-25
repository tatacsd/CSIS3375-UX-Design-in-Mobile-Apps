package com.thayscasado.doginstagram;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.thayscasado.doginstagram.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    ActivityMainBinding binding;

    List<Dog> dogslist;
    private DogViewModel dogViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        onPopulate();
        dogViewModel = new ViewModelProvider(this).get(DogViewModel.class);
        dogViewModel.loadDogs(dogslist);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Reset", Snackbar.LENGTH_LONG)
                        .setAction("reset", v ->{
                            onPopulate();
                            dogViewModel.loadDogs(dogslist);
                        }).show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // methods
    private void onPopulate(){
        dogslist = new ArrayList<>();
        // read the files
        InputStream inputStream = getResources().openRawResource(R.raw.doginfo);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            String CSVLine;
            while((CSVLine = bufferedReader.readLine()) != null){
                String tokens[] = CSVLine.split(",");
                Integer id = Integer.parseInt(tokens[0]);
                String pictureNameForm = tokens[1];
                String breed = tokens[2];
                String name = tokens[3];
                String dateOfBirthForm = tokens[4];

                // get picture resource
                int pictureName = getResources().getIdentifier(pictureNameForm, "drawable", getPackageName());
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthForm, dateTimeFormatter);

                // create the dog and add to the list
                Dog dog = new Dog(id,pictureName, breed, name, dateOfBirth);
                dogslist.add(dog);
            }

        }catch (Exception ex){
            Log.d("ERROR Main", ex.getMessage());
        }
    }
}