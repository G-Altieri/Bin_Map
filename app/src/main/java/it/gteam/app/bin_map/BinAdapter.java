package it.gteam.app.bin_map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.gteam.app.bin_map.databinding.AdapterBinBinding;
import it.gteam.app.bin_map.model.Bin;

public class BinAdapter extends RecyclerView.Adapter<BinAdapter.ViewHolder> {

    private List<Bin> data;
    private AdapterBinBinding binding;
    public BinAdapter(List<Bin> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = AdapterBinBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private AdapterBinBinding binding;
        public ViewHolder(AdapterBinBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.layoutLinear.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bin bin = data.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putSerializable(BinDetailActivity.EXTRA_BIN, bin);
            Navigation.findNavController(v).navigate(R.id.action_menu_list_to_binDetailActivity, bundle);
        }

        public void onBind(Bin bin) {
            String tipoCestino = bin.getType().toLowerCase();
        switch (tipoCestino){
            case "vetro":
                this.binding.imageViewCestinoElementoLista.setImageResource(R.drawable.cestino_blu);
                break;
            case "carta":
                this.binding.imageViewCestinoElementoLista.setImageResource(R.drawable.cestino_verde);
                break;
            case "indifferenziato":
                this.binding.imageViewCestinoElementoLista.setImageResource(R.drawable.cestino_grigio);
                break;
            case "plastica e metalli":
                this.binding.imageViewCestinoElementoLista.setImageResource(R.drawable.cestino_giallo);
                break;
            default:
                this.binding.imageViewCestinoElementoLista.setImageResource(R.drawable.moon);
        }
            binding.setBin(bin);
        }
    }
}
