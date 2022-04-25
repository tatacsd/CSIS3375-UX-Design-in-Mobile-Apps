package com.thayscasado.doginstagram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.thayscasado.doginstagram.databinding.FragmentSecondBinding;

import java.util.ArrayList;

public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null){
            ArrayList<String> names = getArguments().getStringArrayList("Names");
            int numLikes = getArguments().getInt("NumLikes");
            String favorites = "The total likes are: " + numLikes +"\nLiked dogs:\n";
            for (int i =0; i < names.size(); i++) {
               favorites += names.get(i) + "\n";
            }
            binding.textviewSecond.setText(favorites);
        }
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}