package it.gteam.app.bin_map;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

//import com.github.barteksc.pdfviewer.PDFView;

import com.github.barteksc.pdfviewer.PDFView;

import it.gteam.app.bin_map.databinding.FragmentCalendarBinding;
import it.gteam.app.bin_map.databinding.FragmentListBinding;


public class CalendarFragment extends Fragment {
    private FragmentCalendarBinding binding;

    PDFView pdfView;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCalendarBinding.inflate(inflater, container, false);

        //Caricamento Pdf Nella View
        binding.pdfView.fromAsset("CalendarioRifiuti.pdf").load();

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}