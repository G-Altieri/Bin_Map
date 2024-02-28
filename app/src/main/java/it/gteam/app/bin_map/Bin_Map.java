package it.gteam.app.bin_map;

import android.app.Application;

import it.gteam.app.bin_map.service.Repository;

public class Bin_Map extends Application {

    private Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = new Repository();
    }

    public Repository getRepository(){
        return repository;
    }
}
