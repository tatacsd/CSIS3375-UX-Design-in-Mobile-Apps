package com.example.lec9fragdemo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.lec9fragdemo.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lec9fragdemo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        //setContentView(view);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        ConstraintLayout mainLayout = binding.mainContent.mainLayout;
        mainLayout.setBackgroundColor(
                Color.parseColor("#FAFAFA")); //sets the default solid color
        binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

        ViewCompat.setBackgroundTintList(binding.fab,ColorStateList.valueOf(Color.LTGRAY));
        ViewCompat.setBackgroundTintMode(binding.fab, PorterDuff.Mode.SRC_ATOP);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable colorDrawable = (ColorDrawable) mainLayout.getBackground();
                int colorId = colorDrawable.getColor(); //note that getColor() will not work unless you have solid color

                if (colorId != Color.LTGRAY){
                    mainLayout.setBackgroundColor(Color.LTGRAY);
                    binding.fab.setBackgroundTintList(ColorStateList
                            .valueOf(Color.parseColor("#FAFAFA")));
                    ViewCompat.setBackgroundTintList(binding.fab,ColorStateList.valueOf(Color.parseColor("#FAFAFA")));

                } else {
                    mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                    ViewCompat.setBackgroundTintList(binding.fab,ColorStateList.valueOf(Color.LTGRAY));
                }
                Snackbar.make(view, "How do you like this color?", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", (View v) -> {
                            if (colorId == Color.LTGRAY){
                                mainLayout.setBackgroundColor(Color.LTGRAY);
                                binding.fab.setBackgroundTintList(ColorStateList
                                        .valueOf(Color.parseColor("#FAFAFA")));
                            } else {
                                mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));
                                binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                            }

                        }).show();
            }
        });

        List<ColorSpec> ColorsSpecs = new ArrayList<>();
        List<String> ColorDescs = new ArrayList<>(
                            Arrays.asList("BLACK","ORANGE","PURPLE"));
        List<Integer> ColorVals = new ArrayList<>(Arrays.asList(R.color.black,
                                            Color.rgb(255,165,0),
                                            Color.parseColor("#800080")));

        for (int i = 0; i < ColorDescs.size(); i++){
            ColorSpec eachColor = new ColorSpec(ColorDescs.get(i),ColorVals.get(i));
            ColorsSpecs.add(eachColor);
        }

        colorSpecViewModel = new ViewModelProvider(this)
                                    .get(ColorSpecViewModel.class);
        colorSpecViewModel.loadColors(ColorsSpecs);

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
            Toast.makeText(MainActivity.this,
                    "Clicked on Settings", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_search) {
            Toast.makeText(MainActivity.this,
                    "Clicked on Search", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_userprofile){
            Toast.makeText(MainActivity.this,
                    "Clicked on User Profile", Toast.LENGTH_SHORT).show();
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