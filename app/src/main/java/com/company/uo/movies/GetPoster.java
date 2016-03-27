package com.company.uo.movies;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Utku on 26.03.2016.
 */
public class GetPoster extends AsyncTask<String, Void, Bitmap>{
    private Movie movie;

    GetPoster(Movie m){
        movie = m;
    }
    protected Bitmap doInBackground(String... urls){
        String urldisplay = urls[0];
        Bitmap p = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            p = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return p;
    }
    protected void onPostExecute(Bitmap posterdown){
        movie.setPoster(posterdown);
    }
}
