package com.company.uo.movies;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Utku on 26.03.2016.
 */
public class GetMovie extends AsyncTask<String, Void, String>{

    private String response;
    private Movie m;
    static final String OmdbApi = "http://www.omdbapi.com/?";

    GetMovie(Movie movie){
        m = movie;
    }

    protected String doInBackground(String... name){
        String in = name[0];
        in = in.replaceAll(" ", "+");

        try {
            URL apiurl = new URL(OmdbApi + "t="+in+"&plot=short&r=json");
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
            in = "THERE WAS AN ERROR"; //Error Handling yap artık
            response = in;
        }
        else {
            try {
                JSONObject Json = new JSONObject(in);
                response = "Title: " + Json.getString("Title") + "\n" +
                        "Released: " + Json.getString("Released") + "\n" +
                        "Genre: " + Json.getString("Genre") + "\n" +
                        "Runtime: " + Json.getString("Runtime") + "\n" +
                        "Plot: " + Json.getString("Plot") + "\n" +
                        "Actors: " + Json.getString("Actors") + "\n" +
                        "Awards: " + Json.getString("Awards") + "\n";
                m.setResponse(response);

            }
            catch(Exception e){
                Log.e("ERROR", e.getMessage(), e);
            }
        }
       /* try {
            JSONObject forecastJson = new JSONObject(in);

            if (forecastJson.getString("Title").equalsIgnoreCase(input.getText().toString())){
                new GetPoster(poster).execute(forecastJson.getString("Poster"));
                response.setText("Title: " + forecastJson.getString("Title") + "\n\n" +
                        "Released: " + forecastJson.getString("Released") + "\n\n" +
                        "Genre: " + forecastJson.getString("Genre") + "\n\n" +
                        "Runtime: " + forecastJson.getString("Runtime") + "\n\n" +
                        "Plot: " + forecastJson.getString("Plot") + "\n\n" +
                        "Actors: " + forecastJson.getString("Actors") + "\n\n" +
                        "Awards: " + forecastJson.getString("Awards") + "\n\n");
            }
            else {
                response.setText("No match found.");
            }
        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }
        */
    }
}
