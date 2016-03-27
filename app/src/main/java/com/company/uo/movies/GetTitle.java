package com.company.uo.movies;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Utku on 26.03.2016.
 */
public class GetTitle extends AsyncTask<Void, Void, String> {

    private EditText input;
    private JSONObject jreturn;
    private  MovieList movieList;
    private ListView list;
    private Activity activity;
    static final String OmdbApi = "http://www.omdbapi.com/?";

    GetTitle(EditText userinput, ListView l, Activity active){
        input = userinput;
        list = l;
        activity = active;
    }
    protected String doInBackground(Void... urls){
        String in = input.getText().toString();
        in = in.replaceAll(" ", "+");

        try {
            URL apiurl = new URL(OmdbApi + "s="+in+"&y=&plot=short&r=json");
            HttpURLConnection urlConnection = (HttpURLConnection) apiurl.openConnection();
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = buffer.readLine()) != null) {
                    stringBuilder.append(line).append("/n");
                }
                buffer.close();
                return stringBuilder.toString();
            }
            finally {
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }
    protected void onPostExecute(String in){
        if(in == null) {
            in = "THERE WAS AN ERROR"; //Error Handling Lazım
        }
        else {
            try {
                jreturn = new JSONObject(in);
                movieList = new MovieList(jreturn);
                MovieAdapter adapter = new MovieAdapter(activity, movieList.returnarray());
                list.setAdapter(adapter);
            }
            catch(Exception e){
                Log.e("ERROR", e.getMessage(), e);
            }
        }
    }
}
