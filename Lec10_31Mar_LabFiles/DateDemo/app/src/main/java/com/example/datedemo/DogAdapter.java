package com.example.datedemo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datedemo.databinding.LayoutDogitemBinding;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    List<Dog> AdapterDogData;

    // Add binding and the config on gradle



    public DogAdapter(List<Dog> adapterDogData) {
        AdapterDogData = adapterDogData;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the binding
        //using binding create holder object
        //set up click listener on the holder itemView
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutDogitemBinding binding = LayoutDogitemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        DogViewHolder holder = new DogViewHolder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return AdapterDogData.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {

        //We will set up the binding as a property of the ViewHolder
        //in order to access views inside it in onBindViewHolder
        LayoutDogitemBinding holderbinding;

        //Customize the constructor to use binding
        //instead of View to create the holder object
        public DogViewHolder(View itemView) {
            super(itemView);
            holderbinding = binding;
        }
    }
    


}
