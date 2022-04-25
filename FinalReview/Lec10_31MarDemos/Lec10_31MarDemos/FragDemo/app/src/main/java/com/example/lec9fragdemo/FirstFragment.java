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
    List<ColorSpec> FragColors; //fragment data that will be observed from the view model
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                int selIndex = binding.spinnerColors.getSelectedItemPosition();
                int colorVal = FragColors.get(selIndex).getColorVal(); //int color value
                bundle.putInt("COLORVAL",colorVal);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });
        ColorSpecViewModel colorSpecViewModel
                = new ViewModelProvider(requireActivity())
                                                    .get(ColorSpecViewModel.class);
        colorSpecViewModel.getColors().observe(requireActivity(), new Observer<List<ColorSpec>>() {
            @Override
            public void onChanged(List<ColorSpec> colorSpecs) {
                FragColors = colorSpecs; //updating the fragment data
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