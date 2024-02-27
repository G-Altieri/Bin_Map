package it.gteam.app.bin_map.service;

import android.content.Context;

import org.chromium.net.CronetEngine;

public class Request {

    // START SINGLETON
    private static volatile Request istance = null;

    public static synchronized Request getInstance(Context context) {
        if(istance == null) {
            //se è null anche per la classe ne creiamo una nuova
            synchronized (Request.class){
                if(istance == null) istance = new Request(context); //creazione istanza

            }
        }
        return istance;
    }

    private Request(Context context){

        engine = new CronetEngine.Builder(context)
                .enableHttp2(true)
                .enableQuic(true)
                .enableBrotli(true)
                .setStoragePath(context.getCacheDir().getAbsolutePath())
                .enableHttpCache(CronetEngine.Builder.HTTP_CACHE_DISK, 10 * 1024 * 1024)
                .build();


    }

    // END SINGLETON


    private CronetEngine engine;
}
