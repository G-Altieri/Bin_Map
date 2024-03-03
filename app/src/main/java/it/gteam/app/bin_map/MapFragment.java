package it.gteam.app.bin_map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import it.gteam.app.bin_map.model.Bin;
import it.gteam.app.bin_map.databinding.FragmentMapBinding;
import it.gteam.app.bin_map.service.LocationHelper;
import it.gteam.app.bin_map.service.MainViewModel;

public class MapFragment extends Fragment implements OnMapReadyCallback, LocationListener{


        private GoogleMap map;

        private Marker marker;

        private List<Bin> bins = new ArrayList<>();

        private MainViewModel mainViewModel;

        private FragmentMapBinding binding;

        private ActivityResultLauncher<String> permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult (Boolean granted) {
                        if(granted){
                            LocationHelper.start(requireContext(), MapFragment.this);
                        } else {
                            Toast.makeText(requireContext(),
                                    R.string.location_required,
                                    Toast.LENGTH_SHORT)
                                    .show();

                        }
                    }
                }
        );

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentMap);

        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        int fineLocation = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (fineLocation == PackageManager.PERMISSION_DENIED){
            permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

        public void onResume (){
        super.onResume();

        int fineLocation = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (fineLocation == PackageManager.PERMISSION_GRANTED){
            LocationHelper.start(requireContext(), this);
        }
    }

        @Override
        public void onStop() {
        super.onStop();
        LocationHelper.stop(requireContext(), this);
    }

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {

                Bin bin = (Bin) marker.getTag();
                if (bin != null) { //se non nulla
                Bundle bundle = new Bundle();
                bundle.putSerializable(BinDetailActivity.EXTRA_BIN, bin);

                Navigation.findNavController(requireView()).navigate(R.id.action_menu_map_to_binDetailActivity, bundle);
                }
            }
        });
        showMarkers();

    }

        @Override
        public void onLocationChanged(@NonNull Location location) {

     //   map.clear();

        LatLngBounds.Builder bounds = new LatLngBounds.Builder();
        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
        bounds.include(currentPosition);

        if (marker == null) {
            MarkerOptions opt = new MarkerOptions();
            opt.title("My Location");
            opt.position(currentPosition);
            marker=map.addMarker(opt);
        } else {
            marker.setPosition(currentPosition);
        }

        new Thread(() -> {
            if (!bins.isEmpty()) {

                for (Bin bin : bins) {
                    Location binLocation = new Location("bin");
                    binLocation.setLatitude(bin.getLatitudine());
                    binLocation.setLongitude(bin.getLongitudine());
                    if (binLocation.distanceTo(location) >= 10000) continue;

                    bounds.include(new LatLng(bin.getLatitudine(), bin.getLongitudine()));
                }
            }

            binding.getRoot().post(() -> {
                map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 16));
            });
        }).start();
    }

        private void showMarkers(){
        mainViewModel.getBins()
                .observe(getViewLifecycleOwner(), bins -> {
                    MapFragment.this.bins = bins;
                    map.clear();
                    bins.forEach(this::createBin);
                });
    }

        private void createBin(Bin bin){


        MarkerOptions options = new MarkerOptions();
        options.title(bin.getType());
        options.position(new LatLng(bin.getLatitudine(), bin.getLongitudine()));


            switch (bin.getType().toLowerCase()){
                case "vetro":
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    break;
                case "carta":
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    break;
                case "indifferenziato":
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                    break;
                case "plastica e metalli":
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    break;
                default:
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
            }



        Marker marker =map.addMarker(options);
        marker.setTag(bin);

    }


}

