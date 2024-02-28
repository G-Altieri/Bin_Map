package it.gteam.app.bin_map.service;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.gteam.app.bin_map.Bin_Map;
import it.gteam.app.bin_map.databinding.FragmentListBinding;
import it.gteam.app.bin_map.model.Bin;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Bin>> bins = new MutableLiveData<>();

    private Repository repository;

    public MainViewModel(@NonNull Application application){
        super(application);

        repository = ((Bin_Map)application).getRepository();
        repository.downloadData(application, new Request.RequestCallback() {
            @Override
            public void onCompleted(UrlRequest request, UrlResponseInfo info, byte[] data, CronetException error) {

                if (data != null) {
                String response = new String(data);
                }
            }
        });
    }

    public LiveData<List<Bin>> getStations(){
        return bins;
    }
}
