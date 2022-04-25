package com.thayscasado.doginstagram;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.thayscasado.doginstagram.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    List<Dog> dogList = new ArrayList<>();
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
        DogViewModel dogViewModel = new ViewModelProvider(requireActivity()).get(DogViewModel.class);
        dogViewModel.getDogs().observe(requireActivity(), dogs -> {
            dogList = dogs;
            GridLayoutManager gm = new GridLayoutManager(getParentFragment().getContext(), 2);
            binding.recyclerViewDogItems.setLayoutManager(gm);
            binding.recyclerViewDogItems.setAdapter(new DogAdapter(dogs));
        });
        binding.buttonGoToSecondFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pass list of favorites
                ArrayList<String> names = new ArrayList<>();
                int numLikes = 0;
                for (int i =0; i < dogList.size(); i++) {
                    if(dogList.get(i).getNumLikes() != 0) {
                        names.add(dogList.get(i).getName());
                        numLikes++;
                    }
                }

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("Names", names);
                bundle.putInt("NumLikes", numLikes);

                // get the number of favorites and their names
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

}