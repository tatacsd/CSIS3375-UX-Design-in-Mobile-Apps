package com.example.recyclerviewtabsdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtabsdemo.model.Tune;

import java.util.List;

public class TuneAdapter extends RecyclerView.Adapter<TuneAdapter.TuneViewHolder> {

    List<Tune> TuneList;

    public TuneAdapter(List<Tune> tuneList) {
        TuneList = tuneList;
    }

    public List<Tune> getTuneList() {
        return TuneList;
    }

    public void setTuneList(List<Tune> tuneList) {
        TuneList = tuneList;
        notifyDataSetChanged(); //this means anytime data changes, adapter data needs to be repopulated
    }

    @NonNull
    @Override
    public TuneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate external layout and get itemView from inflated layout
        View itemView = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.layout_tune,parent,false);
        //Create TuneViewHolder object using this itemView
        TuneViewHolder holder = new TuneViewHolder(itemView);

        //Set imageview and txtview inside the holder object by finding the needed ids
        holder.imgViewTune = itemView.findViewById(R.id.imgViewTune);
        holder.txtViewTune = itemView.findViewById(R.id.txtViewTune);
        holder.tuneItemView = itemView; //not needed at all

        holder.tuneItemView.setBackgroundColor(Color.parseColor("#FAFAFA")); //resets color when view holder object is created
        holder.tuneItemView.setOnClickListener((View view) -> {
                if (holder.tuneItemView.getBackground() instanceof ColorDrawable
                    && ((ColorDrawable) holder.tuneItemView.getBackground()).getColor()
                                                    != Color.LTGRAY) {
                    holder.tuneItemView.setBackgroundColor(Color.LTGRAY);
                } else {
                    holder.tuneItemView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                }

        });
        //return view holder object
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder holder, int position) {
            holder.tuneItemView.setBackgroundColor(Color.parseColor("#FAFAFA"));
            holder.txtViewTune.setText(TuneList.get(position).getTuneName());
            holder.imgViewTune.setImageResource(TuneList.get(position).getTunePic());
    }

    @Override
    public int getItemCount() {
        return TuneList.size();
    }

    public class TuneViewHolder extends RecyclerView.ViewHolder{

        ImageView imgViewTune;
        TextView txtViewTune;
        View tuneItemView; //not really needed because holder.itemView

        public TuneViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
