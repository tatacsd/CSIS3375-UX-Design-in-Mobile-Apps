package com.example.datedemo;


import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datedemo.databinding.LayoutDogitemBinding;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    List<Dog> AdapterDogData;
    OnItemClickListener DogItemClickListener;

    public DogAdapter(List<Dog> adapterDogData, OnItemClickListener dogItemClickListener) {
        AdapterDogData = adapterDogData;
        DogItemClickListener = dogItemClickListener;
    }

    public DogAdapter(List<Dog> adapterDogData) {
        AdapterDogData = adapterDogData;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflate the binding
        //using binding create holder object
        //set up click listener on the holder itemView
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutDogitemBinding binding
                = LayoutDogitemBinding.inflate(inflater, parent, false);

        DogViewHolder holder = new DogViewHolder(binding);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DogItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        holder.holderbinding.txtViewId.setText(String.valueOf(
                AdapterDogData.get(position).getId()));
        holder.holderbinding.txtViewName.setText(AdapterDogData.get(position).getDogName());
        holder.holderbinding.txtViewBreed.setText(AdapterDogData.get(position).getDogBreed());
        holder.holderbinding.imgViewDogPic
                    .setImageResource(AdapterDogData.get(position).getDogPicDrawable());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        holder.holderbinding.txtViewDOB
                .setText(formatter.format(AdapterDogData.get(position).getDob()));
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
        public DogViewHolder(LayoutDogitemBinding binding) {
            super(binding.getRoot());
            holderbinding = binding;
        }
    }
    
    public interface OnItemClickListener {
        void onItemClick(int i);
    }

}
