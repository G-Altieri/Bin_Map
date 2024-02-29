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
//https://vivicalascio.altervista.org/BinMap/dati.json
    private boolean isDarkModeEnable;
    private ImageView buttonCambioTema;
    private ImageView buttonArrowBackToolBar;

    private NavController navController;

    private SharedPreferences sharedPreferences;

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
        buttonCambioTema = (ImageView) findViewById(R.id.buttonTestTheme);

        sharedPreferences = getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
        isDarkModeEnable = sharedPreferences.getBoolean("isDarkModeEnable",false);

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



        // questo fa funzionare il bottomNavView tramite il click
        NavHostFragment fragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerViewMainActivity);
        if (fragment != null) {
            navController = fragment.getNavController();
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
        }

        //Codice arrow back

        //Button back Arrow
         buttonArrowBackToolBar = (ImageView) findViewById(R.id.buttonBack);
        buttonArrowBackToolBar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navController.navigate(R.id.menu_home);
            }
        });


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Log.v("Posizione Attuale", "onDestinationChanged: "+destination.getLabel());
                String paginaCorrente = (String) destination.getLabel();
                if (paginaCorrente.contains("calendar")) {
                    Log.v("Posizione AttualeAAAAAAAAAAAA", "SIIIIIIIIIIIII");
                    buttonArrowBackToolBar.setVisibility(View.VISIBLE);
                }else{
                    Log.v("Posizione AttualeAAAAAAAAAAAA", "NOOOOOOOOOOOO");
                    buttonArrowBackToolBar.setVisibility(View.GONE);
                }

            }
        });



        //Fine arrow back


    }

    private void toggleDarkMode(){
        if(isDarkModeEnable){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isDarkModeEnable=false;
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            isDarkModeEnable=true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isDarkModeEnable",isDarkModeEnable);
        editor.apply();
    }
}