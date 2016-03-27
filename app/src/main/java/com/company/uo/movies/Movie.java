package com.company.uo.movies;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

/**
 * Created by Utku on 26.03.2016.
 */
public class Movie {
    private String title;
    private String posterUrl;
    private Bitmap poster;
    private String response;

    Movie(JSONObject jsonObject){
        try{
            title = jsonObject.getString("Title");
            new GetMovie(this).execute(title);
            posterUrl = jsonObject.getString("Poster");
            new GetPoster(this).execute(posterUrl);

        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }
    }

    protected void setResponse(String s){
        response = s;
    }
    protected void setPoster(Bitmap p){
        poster = p;
    }
    protected String returnresponse(){
        return  response;
    }
    protected Bitmap returnposter(){
        return poster;
    }
}
