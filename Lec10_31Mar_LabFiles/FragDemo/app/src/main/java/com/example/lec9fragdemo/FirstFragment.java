package com.example.lec9fragdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lec9fragdemo.databinding.FragmentFirstBinding;

import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    // add the fragment data that will be observed
    List<ColorSpec> fragColor;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Creates the binding -> two ways 1 container or parent ways
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    // Create after the onCreatView -> that is why you can use the binding to get all items
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Navigate from first fragment to the second
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // passing the text value to the other fragment
                Bundle bundle = new Bundle();
                int setIndex = binding.spinnerColors.getSelectedItemPosition();
                int colorVal = fragColor.get(setIndex).getColorVal(); // get the color in use
                bundle.putInt("COLORVAL", colorVal);


                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });

        // The owner of the view model
        ColorSpecViewModel colorSpecViewModel = new ViewModelProvider(
                // get activity or require activity (better because gets the right activity the one hosting the fragment)
                requireActivity()).get(ColorSpecViewModel.class);

        // Set the observer (interface that gets the list and observe the LiveData changes)
        colorSpecViewModel.getColors().observe(requireActivity(), new Observer<List<ColorSpec>>() {
            @Override
            public void onChanged(List<ColorSpec> colorSpecs) {
                // when change i want to change the fragment data
                fragColor = colorSpecs;
                binding.spinnerColors.setAdapter(new ColorSpecAdapter(colorSpecs));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}