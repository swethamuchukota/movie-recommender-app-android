package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.MessageFormat;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        String selectedMovie = getIntent().getStringExtra("movieDetails");
        String imdbId = selectedMovie.substring(selectedMovie.lastIndexOf(':')+1, selectedMovie.length());
        String movieUri = MessageFormat.format(CommonUtils.MOVIES_API_FORAMT, imdbId);
        new GetMovieDetailsTask().execute(movieUri);
    }

    private class GetMovieDetailsTask extends AsyncTask<String, Void, MovieMetadata> {

        @Override
        protected MovieMetadata doInBackground(String... params) {
            MovieMetadata metadata = CommonUtils.get(params[0], MovieMetadata.class);
            return metadata;
        }

        @Override
        protected void onPostExecute(MovieMetadata result) {
            TextView txt = (TextView) findViewById(R.id.movieTitle);
            txt.setText(result.getTitle());
            TextView details = (TextView) findViewById(R.id.movieDetailsTextView);
            details.setText(result.toString());
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
            ImageView img= (ImageView) findViewById(R.id.movieImageView);
            // show The Image in a ImageView
            new DownloadImageTask(img)
                    .execute(result.getCov());

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
