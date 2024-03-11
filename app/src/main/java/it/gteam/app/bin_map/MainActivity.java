package it.gteam.app.bin_map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import it.gteam.app.bin_map.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private boolean isDarkModeEnable;
    private ImageView buttonCambioTema;
    private ImageView buttonArrowBackToolBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //Toolbar
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Fine Toolbar

        //Cambio Tema
        buttonCambioTema = (ImageView) findViewById(R.id.buttonTestTheme);

        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.themeName, outValue, true);
        if ("dark".equals(outValue.string)) {
          isDarkModeEnable = true;
        }else{
            isDarkModeEnable = false;
        }
        if(isDarkModeEnable){
            buttonCambioTema.setImageResource(R.drawable.sun);
        } else {
            buttonCambioTema.setImageResource(R.drawable.moon);
        }
        buttonCambioTema.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggleDarkMode();
            }
        });
        //Fine Cambio Tema

        //Codice Freccia Indietro
        buttonArrowBackToolBar = (ImageView) findViewById(R.id.buttonBack);

        //Collegamento navbar
        NavHostFragment fragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerViewMainActivity);
        if (fragment != null) {
            NavController navController = fragment.getNavController();
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);


            navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                    String paginaCorrente = (String) destination.getLabel();
                    if (paginaCorrente.contains("calendar")||paginaCorrente.contains("dovelobutto")) {
                        buttonArrowBackToolBar.setVisibility(View.VISIBLE);
                    }else{
                        buttonArrowBackToolBar.setVisibility(View.GONE);
                    }
                }
            });

            buttonArrowBackToolBar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    navController.navigate(R.id.menu_home);
                }
            });
        }
    }

    //Funzione per lo switch di tema
    private void toggleDarkMode(){
        if(isDarkModeEnable){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isDarkModeEnable=false;
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            isDarkModeEnable=true;
        }
    }

}