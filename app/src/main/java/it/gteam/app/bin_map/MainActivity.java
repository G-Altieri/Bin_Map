package it.gteam.app.bin_map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import it.gteam.app.bin_map.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
//https://vivicalascio.altervista.org/BinMap/dati.json
    private boolean isDarkModeEnable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater()); //posso fare getLayoutInflater perché sono in un'activity e non in un frammento

        setContentView(binding.getRoot());

        //Aggiunta ToolBAr
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText("Bin Map Home");

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Fine Toolbar

        //Codice Cambio Tema
        Button buttonCambioTema = findViewById(R.id.buttonTestTheme);
        SharedPreferences sharedPreferences= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        isDarkModeEnable = sharedPreferences.getBoolean("isDarkModeEnable",false);
        if(isDarkModeEnable){
          //  AppCompatActivity.setDefaultNightMode();
        }else{

        }

        //FIne Cambio Tema



        // questo fa funzionare il bottomNavView tramite il click
        NavHostFragment fragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerViewMainActivity);
        if (fragment != null) {
            NavController controller = fragment.getNavController();
            NavigationUI.setupWithNavController(binding.bottomNavigationView, controller);
        }
    }
}