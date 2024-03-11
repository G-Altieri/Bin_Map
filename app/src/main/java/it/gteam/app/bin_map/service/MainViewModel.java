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
import it.gteam.app.bin_map.database.DB;
import it.gteam.app.bin_map.databinding.FragmentListBinding;
import it.gteam.app.bin_map.model.Bin;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Bin>> bins = new MutableLiveData<>();

    private Repository repository;

    public MainViewModel(@NonNull Application application){
        super(application);

        new Thread(() -> {
        repository = ((Bin_Map)application).getRepository();
        List<Bin> list = DB.getInstance(application).getBinDAO().findAll();

        if(list.isEmpty()) {
            repository.downloadData(application, new Request.RequestCallback() {

                @Override
                public void onCompleted(UrlRequest request, UrlResponseInfo info, byte[] data, CronetException error) {

                List<Bin> tempBins = new ArrayList<>();

                if (data != null) {
                    String response = new String(data);
                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i=0; i<array.length(); i++) {
                            JSONObject item = array.optJSONObject(i);
                            Bin bin = Bin.parseJson(item);
                            if (bin != null) tempBins.add(bin);
                        }
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                } else {
                    if (error != null){
                        error.printStackTrace();
                    }
                }

                DB.getInstance(getApplication()).getBinDAO().insert(tempBins);
                bins.postValue(tempBins);
                }
            });
        } else {
            bins.postValue(list);//non faccio postValue perche non sto nella callBack o messo post perche ora e in un thread
        }
        }).start();
    }

    public LiveData<List<Bin>> getBins(){
        return bins;
    }
}
