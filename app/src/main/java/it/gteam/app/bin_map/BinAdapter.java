package it.gteam.app.bin_map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.gteam.app.bin_map.databinding.AdapterBinBinding;
import it.gteam.app.bin_map.model.Bin;

public class BinAdapter extends RecyclerView.Adapter<BinAdapter.ViewHolder> {

    private List<Bin> data;

    public BinAdapter(List<Bin> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AdapterBinBinding binding = AdapterBinBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bin bin = data.get(position);
        holder.onBind(bin);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private AdapterBinBinding binding;
        public ViewHolder(AdapterBinBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void onBind(Bin bin) {

            binding.setBin(bin);
        }
    }
}
