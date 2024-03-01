package it.gteam.app.bin_map;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import it.gteam.app.bin_map.databinding.ActivityBinDetailBinding;
import it.gteam.app.bin_map.model.Bin;

public class BinDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BIN = "extra_bin";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBinDetailBinding binding = ActivityBinDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bin bin = (Bin) getIntent().getSerializableExtra(EXTRA_BIN);
        if (bin != null){
            binding.setBin(bin);
        }
    }
}
