package com.company.uo.movies;

import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Utku on 26.03.2016.
 */
public class MovieList {
    private ArrayList<Movie> moviearray = new ArrayList<Movie>();
    private JSONArray jsonArray;

    MovieList(JSONObject array){
        try{
        jsonArray = array.getJSONArray("Search");}
        catch (Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }
        for (int i=0; i< jsonArray.length(); i++){
            try{
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Movie toadd = new Movie(jsonObject);
                moviearray.add(i,toadd);
            }
            catch(Exception e){
                Log.e("ERROR", e.getMessage(), e);
            }
        }
    }
    protected ArrayList<Movie> returnarray(){
        return moviearray;
    }

}
