package it.gteam.app.bin_map.service;

import android.content.Context;

import java.util.ConcurrentModificationException;

import it.gteam.app.bin_map.service.Request;

public class Repository {

    public void downloadData (Context context, Request.RequestCallback callback){
        Request.getInstance(context).requestDownload(callback);
    }
}
