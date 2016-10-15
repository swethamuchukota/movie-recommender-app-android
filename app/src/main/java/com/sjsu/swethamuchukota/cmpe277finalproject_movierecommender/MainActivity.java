package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

import android.content.Intent;
import android.hardware.SensorEventListener;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.*;
import android.widget.SeekBar.*;
import android.util.Log;
import android.app.Activity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {

    private SeekBar dBar = null;
    private SeekBar cBar = null;
    private SeekBar hBar = null;
    private SeekBar aBar = null;

    int dBarChanged = 0;
    int cBarChanged = 0;
    int aBarChanged = 0;
    int hBarChanged = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dBar = (SeekBar) findViewById(R.id.dramaBar);
        cBar = (SeekBar) findViewById(R.id.comedyBar);
        hBar = (SeekBar) findViewById(R.id.horrorBar);
        aBar = (SeekBar) findViewById(R.id.actionBar);

        dBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                dBarChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"seek bar progress:"+dBarChanged,
                        //Toast.LENGTH_SHORT).show();
                Log.d("Drama Value",dBarChanged + "");
            }
        });

        cBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                cBarChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"seek bar progress:"+cBarChanged,
                        //Toast.LENGTH_SHORT).show();
                Log.d("Comedy Value",cBarChanged + "");
            }
        });


        aBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                aBarChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"seek bar progress:"+aBarChanged,
                        //Toast.LENGTH_SHORT).show();

                Log.d("Action Value",aBarChanged + "");
            }
        });

        hBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                hBarChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"seek bar progress:"+hBarChanged,
                       // Toast.LENGTH_SHORT).show();

                Log.d("Horror Value",hBarChanged + "");
            }
        });

    }

    public void onRecommend(View view)
    {
        Log.d("Horror Value",hBarChanged + "");
        Log.d("Action Value",aBarChanged + "");
        Log.d("Comedy Value",cBarChanged + "");
        Log.d("Drama Value",dBarChanged + "");

        int[] barValues = new int[] {hBarChanged,aBarChanged,dBarChanged,cBarChanged};
        String[] genre = new String[] {"Horror","Action","Drama","Comedy"} ;

        JSONArray object = null;
        try {
            object = makJsonObject(barValues, genre, 4);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        final MainActivity mainActivity = this;
        new AsyncTask<JSONArray, Void, List<UserRecommendations>>() {

            @Override
            protected List<UserRecommendations> doInBackground(JSONArray... params) {
                return new MovieService().findMoviesList(params[0]);
            }

            @Override
            protected void onPostExecute(List<UserRecommendations> userRecommendationsList){
                Intent intent = new Intent(mainActivity, MovieDisplayActivity.class);
                String json = CommonUtils.convertObjectToJson(userRecommendationsList);
                System.out.println("Passing user recommendations to display: " + json);
                intent.putExtra("recommendations", json);
                startActivity(intent);
            }
        }.execute(object);
    }

    public JSONArray makJsonObject(int barValues[], String genre[], int noObjects)
            throws JSONException {
        JSONObject obj = null;
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < noObjects; i++) {
            obj = new JSONObject();
            try {
                obj.put("genre", genre[i]);
                obj.put("rating", barValues[i]);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(obj);
        }

        /*JSONObject finalobject = new JSONObject();
        finalobject.put("recommend", jsonArray);*/
        return jsonArray;
    }

}
