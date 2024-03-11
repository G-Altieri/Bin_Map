package it.gteam.app.bin_map;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

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

            switch (bin.getType().toLowerCase()){
                case "vetro":
                    binding.imageViewCestinoActivity.setImageResource(R.drawable.cestino_blu);
                    break;
                case "carta":
                    binding.imageViewCestinoActivity.setImageResource(R.drawable.cestino_verde);
                    break;
                case "indifferenziato":
                    binding.imageViewCestinoActivity.setImageResource(R.drawable.cestino_grigio);
                    break;
                case "plastica e metalli":
                    binding.imageViewCestinoActivity.setImageResource(R.drawable.cestino_giallo);
                    break;
                default:
                    binding.imageViewCestinoActivity.setImageResource(R.drawable.moon);
            }

            binding.setBin(bin);
        }

        binding.buttonArrowBackActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}
