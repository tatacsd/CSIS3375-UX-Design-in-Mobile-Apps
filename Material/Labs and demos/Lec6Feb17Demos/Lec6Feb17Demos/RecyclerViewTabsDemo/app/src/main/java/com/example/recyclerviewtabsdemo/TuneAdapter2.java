package com.example.recyclerviewtabsdemo;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtabsdemo.model.Tune;

import java.util.List;

public class TuneAdapter2 extends RecyclerView.Adapter<TuneAdapter2.TuneViewHolder2> {
    List<Tune> TuneList;
    int CurrentPlayInd = -1;

    public TuneAdapter2(List<Tune> tuneList) {
        TuneList = tuneList;
    }

    public List<Tune> getTuneList() {
        return TuneList;
    }

    public void setTuneList(List<Tune> tuneList) {
        TuneList = tuneList;
        notifyDataSetChanged();
    }

    public int getCurrentPlayInd() {
        return CurrentPlayInd;
    }

    public void setCurrentPlayInd(int currentPlayInd) {
        CurrentPlayInd = currentPlayInd;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TuneViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_tune2,parent,false);

        TuneViewHolder2 holder2 = new TuneViewHolder2(itemView);
        holder2.imgViewTune2 = itemView.findViewById(R.id.imgViewTune2);
        holder2.txtViewTune2 = itemView.findViewById(R.id.txtViewTune2);
        holder2.imgViewPlayPause = itemView.findViewById(R.id.imgViewPlayPause);

        //I could set up click listeners here. Or in onBindViewHolder

        return holder2;
    }

    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder2 holder, @SuppressLint("RecyclerView") int position) {
        holder.txtViewTune2.setText(TuneList.get(position).getTuneName());
        holder.imgViewTune2.setImageResource(TuneList.get(position).getTunePic());
        if (position == CurrentPlayInd) {
            holder.imgViewPlayPause.setImageResource(R.drawable.pause);
        } else {
            holder.imgViewPlayPause.setImageResource(R.drawable.play);
        }

        holder.imgViewPlayPause.setOnClickListener((View view) -> {
            if (CurrentPlayInd == position){
                CurrentPlayInd = -1; //stops playing currently playing song
                notifyDataSetChanged(); //getAdapterPosition() is not kosher
            } else {
                CurrentPlayInd = position;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return TuneList.size();
    }

    public class TuneViewHolder2 extends RecyclerView.ViewHolder{
        ImageView imgViewTune2;
        TextView txtViewTune2;
        ImageView imgViewPlayPause;

        public TuneViewHolder2(@NonNull View itemView) {
            super(itemView);
        }
    }
}
