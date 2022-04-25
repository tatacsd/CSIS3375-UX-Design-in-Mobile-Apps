package com.thayscasado.doginstagram;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.thayscasado.doginstagram.databinding.LayoutDogItemBinding;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    List<Dog> dogList;

    public DogAdapter(List<Dog> dogList) {
        this.dogList = dogList;
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {
        private LayoutDogItemBinding layoutDogItemBinding;

        public DogViewHolder(LayoutDogItemBinding layoutDogItemBinding) {
            super(layoutDogItemBinding.getRoot());
            this.layoutDogItemBinding = layoutDogItemBinding;
        }
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate
        LayoutDogItemBinding layoutDogItemBinding = LayoutDogItemBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        DogViewHolder holder = new DogViewHolder(layoutDogItemBinding);

        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
       holder.layoutDogItemBinding.imageViewDog.setImageResource(dogList.get(position).getImageFileName());
       holder.layoutDogItemBinding.textViewName.setText(dogList.get(position).getName());
       holder.layoutDogItemBinding.textViewBreed.setText(dogList.get(position).getBreed());
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       holder.layoutDogItemBinding.textViewDOB.setText(formatter.format(dogList.get(position).getDOB()));
        if(dogList.get(position).getHeartNoHeart() == true){
            holder.layoutDogItemBinding.imageViewLikes.setImageResource(R.drawable.ic_baseline_favorite_24);
        } else {
            holder.layoutDogItemBinding.imageViewLikes.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
       holder.layoutDogItemBinding.textViewLikeNumber.setText(dogList.get(position).getNumLikes().toString());
       // when clicking on the hear add one
        holder.layoutDogItemBinding.imageViewLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change to color one
                if(dogList.get(position).getHeartNoHeart() != true){
                    dogList.get(position).setHeartNoHeart(true);
                    holder.layoutDogItemBinding.imageViewLikes.setImageResource(R.drawable.ic_baseline_favorite_24);
                    dogList.get(position).setNumLikes(1);
                    holder.layoutDogItemBinding.textViewLikeNumber.setText(dogList.get(position).getNumLikes().toString());
                } else {
                    dogList.get(position).setHeartNoHeart(false);
                    holder.layoutDogItemBinding.imageViewLikes.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    dogList.get(position).setNumLikes(0);
                    holder.layoutDogItemBinding.textViewLikeNumber.setText(dogList.get(position).getNumLikes().toString());
                }
                holder.layoutDogItemBinding.textViewLikeNumber.setText(dogList.get(position).getNumLikes().toString());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }

}
