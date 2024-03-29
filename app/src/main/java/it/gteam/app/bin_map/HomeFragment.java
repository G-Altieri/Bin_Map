package it.gteam.app.bin_map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import it.gteam.app.bin_map.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private Button buttonCalendar;

    private WebView mywebView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        //Pulsante per Aprire il calendario, pdf
        binding.buttonHomeCalendar.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                Navigation.findNavController(v)
                        .navigate(R.id.action_menu_home_to_fragment_calendar, bundle);
                   }
               });

        //Pulsante per aprire dove lo butto, website
        binding.buttonDoveLoButto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(v)
                        .navigate(R.id.action_menu_home_to_fragment_dovelobutto, bundle);
            }
        });

        //Pulsante per aprire la lista dei cestini
        binding.buttonTrovaCestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(v)
                        .navigate(R.id.menu_list, bundle);
            }
        });

        return binding.getRoot();//restituisce una View
    }

}
