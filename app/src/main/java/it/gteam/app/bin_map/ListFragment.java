package it.gteam.app.bin_map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import java.util.List;

import it.gteam.app.bin_map.databinding.FragmentListBinding;
import it.gteam.app.bin_map.model.Bin;
import it.gteam.app.bin_map.service.MainViewModel;

public class ListFragment extends Fragment {
    private FragmentListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.getRoot();  // perché restituisce una View
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mainViewModel.getBins().observe(getViewLifecycleOwner(), new Observer<List<Bin>>() {
            @Override
            public void onChanged(List<Bin> bins) {
                binding.recyclerView.setAdapter(new BinAdapter(bins));
            }
        });
    }
}


