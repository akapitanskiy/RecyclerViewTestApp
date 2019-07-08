package com.alexthekap.recyclerviewtestapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class GetURLImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView imgView;

    public GetURLImageTask(ImageView imgView) {
        this.imgView = imgView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bmp = null;
        try {
            InputStream in = new URL(url).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        imgView.setImageBitmap(bmp);
    }
}
