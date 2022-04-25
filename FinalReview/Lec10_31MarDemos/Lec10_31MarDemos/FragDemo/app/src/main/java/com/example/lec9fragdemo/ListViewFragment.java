package com.example.lec9fragdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.lec9fragdemo.databinding.FragmentListViewBinding;

import java.util.List;


public class ListViewFragment extends Fragment {
    FragmentListViewBinding binding;
    List<ColorSpec> FragColors;
    public ListViewFragment() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListViewBinding
                    .inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ColorSpecViewModel colorSpecViewModel
                = new ViewModelProvider(requireActivity())
                        .get(ColorSpecViewModel.class);
        colorSpecViewModel.getColors().observe(requireActivity(),
                (List<ColorSpec> colorSpecs) -> {
                FragColors = colorSpecs;
                binding.listViewColors.setAdapter(new ColorSpecAdapter(colorSpecs));
        });

        binding.listViewColors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i - index of the item in the ListView
                Bundle bundle = new Bundle();
                int colorVal = FragColors.get(i).getColorVal();
                bundle.putInt("COLORVAL",colorVal); //using the same key name for spinner and listview fragment

                NavHostFragment.findNavController(ListViewFragment.this)
                        .navigate(R.id.action_listViewFragment_to_SecondFragment, bundle);
            }
        });

    }
}