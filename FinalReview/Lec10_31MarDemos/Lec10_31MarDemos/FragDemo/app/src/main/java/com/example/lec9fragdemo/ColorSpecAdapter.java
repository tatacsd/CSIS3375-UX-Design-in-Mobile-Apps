package com.example.lec9fragdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lec9fragdemo.databinding.LayoutColoritemBinding;

import java.util.List;

public class ColorSpecAdapter extends BaseAdapter {
    List<ColorSpec> AdapterColorList;
    LayoutColoritemBinding binding; //viewBinding = true must be set up
    public ColorSpecAdapter(List<ColorSpec> adapterColorList) {AdapterColorList = adapterColorList; }
    @Override
    public int getCount() {
        return AdapterColorList.size(); //returns the size of the data
    }
    @Override
    public Object getItem(int i) { return null; //you could return AdapterColorList.get(i)
    }

    @Override
    public long getItemId(int i) {  return 0; //return either the primary id or i
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = LayoutColoritemBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()));
        binding.txtViewColorItem
                .setText(AdapterColorList.get(i).getColorDesc());
        binding.txtViewColorItem
                .setTextColor(AdapterColorList.get(i).getColorVal());

        return binding.getRoot(); //return view of each item
    }
}
