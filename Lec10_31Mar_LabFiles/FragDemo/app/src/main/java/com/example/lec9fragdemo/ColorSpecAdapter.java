package com.example.lec9fragdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lec9fragdemo.databinding.LayoutColoritemBinding;

import java.util.List;

// to be able to custumize each item different using an external layout
public class ColorSpecAdapter extends BaseAdapter {
    // You dont need a inner class for view holder

    // The data colors name and int
    List<ColorSpec> adapterColorList;

    public ColorSpecAdapter(List<ColorSpec> adapterColorList) {
        this.adapterColorList = adapterColorList;
    }



    // Transport to the extra layout
    LayoutColoritemBinding binding; // view binding needs to be setup to true


    @Override
    public int getCount() {
        return adapterColorList.size();
    }


    @Override
    public Object getItem(int i) {
        return null; //return adapterColorList.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0; // return the primary id or 1
    }

    // called repeatedly when call the adapter
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = LayoutColoritemBinding.inflate(LayoutInflater.from(viewGroup.getContext()));

        binding.txtViewColorItem.setText(adapterColorList.get(i).getColorDesc());
        binding.txtViewColorItem.setTextColor(adapterColorList.get(i).getColorVal()); // PAY ATTENTION TO TEXT COLOR
        return binding.getRoot(); //
    }
}
