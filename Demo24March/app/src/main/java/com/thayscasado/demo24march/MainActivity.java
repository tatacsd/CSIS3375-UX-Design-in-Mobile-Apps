package com.thayscasado.demo24march;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.thayscasado.demo24march.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ColorSpecViewModel colorSpecViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        // Get the mainLayout
        ConstraintLayout mainLayout = binding.mainContent.mainLayout;
        //Set the default solid  color
        mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));

        // Color list for the state of the fab using the colorStateList
        binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change the color of the fab to be a constrast of the mainLayout
                ColorDrawable colorDrawable = (ColorDrawable) mainLayout.getBackground();
                int colorID = colorDrawable.getColor(); // get the solid color

                if(colorID != Color.LTGRAY){
                    mainLayout.setBackgroundColor(Color.LTGRAY);
                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAFAFA")));
                } else {
                    mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                }

                // ADD A LISTENER
                Snackbar.make(view, "How do you like this color?", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", (View v) -> {
                            if(colorID == Color.LTGRAY){
                                mainLayout.setBackgroundColor(Color.LTGRAY);
                                binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAFAFA")));
                            } else {
                                mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));
                                binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                            }
                        }).show();
            }
        });

        List<ColorSpec> colorSpecs = new ArrayList<>();
        List<String> colorDescs = new ArrayList<>(Arrays.asList("BLACK", "ORANGE", "PURPLE"));
        List<Integer> colorVals = new ArrayList<>(Arrays.asList( // 3 ways for colors
                R.color.black,
                Color.rgb(255,165,0),
                Color.parseColor("#800080")
        ));

        // Create the list with the desc and the color int
        for (int i = 0; i < colorDescs.size() ; i++) {
            ColorSpec eachColor = new ColorSpec(colorDescs.get(i), colorVals.get(i));
            colorSpecs.add(eachColor);
        }

        // set the owner of the view model
        colorSpecViewModel = new ViewModelProvider(this).get(ColorSpecViewModel.class);
        colorSpecViewModel.loadColors(colorSpecs);
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
            Toast.makeText(MainActivity.this, "Clicked on Settings", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.action_search){
            Toast.makeText(MainActivity.this, "Clicked on Search", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.action_userprofile) {
            Toast.makeText(MainActivity.this, "Clicked on User Profile", Toast.LENGTH_SHORT).show();
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
}