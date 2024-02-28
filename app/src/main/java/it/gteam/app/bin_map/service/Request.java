package it.gteam.app.bin_map.service;

import android.content.Context;

import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    private final Executor executor = Executors.newSingleThreadExecutor();

    public void requestDownload(Request.RequestCallback callback) {
        // Thread Async
        engine.newUrlRequestBuilder("da mette link database .json", callback , executor)
                .build()
                .start();
    }
    public abstract static class RequestCallback extends UrlRequest.Callback{

         private final int BUFFER_SIZE = 1024 * 1024;

         private final ByteArrayOutputStream received = new ByteArrayOutputStream();

         private final WritableByteChannel channel = Channels.newChannel(received);

        @Override
        public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) throws Exception {
            request.followRedirect();
        }

        @Override
        public void onResponseStarted(UrlRequest request, UrlResponseInfo info) throws Exception {
            if(info.getHttpStatusCode() == 200){
                request.read(ByteBuffer.allocateDirect(BUFFER_SIZE));
            }

        }

        @Override
        public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) throws Exception {

            byteBuffer.flip();

            try {
                channel.write(byteBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }

            byteBuffer.clear();
            request.read(byteBuffer);
        }

        @Override
        public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
            byte[] data = received.toByteArray();
            onCompleted(request, info, data, null);
        }

        @Override
        public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
            onCompleted(request, info, null, error);
        }

        // Trhead Asy

        public abstract void onCompleted(UrlRequest request, UrlResponseInfo info, byte[] data, CronetException error);
    }

}
