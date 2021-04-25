package com.pamiranindustries.pamiran;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.net.CookieHandler;
import java.util.Objects;

public class MovieDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        TextView details = findViewById(R.id.movieDet);
        TextView details2 = findViewById(R.id.movieImDet);
        TextView details3 = findViewById(R.id.movieDate);
        String[] movieDetails = getIntent().getStringArrayExtra("ExtraDetail");

//
//        Objects.requireNonNull(getSupportActionBar()).setTitle(movieDetails[0]);
//        getSupportActionBar().setSubtitle(movieDetails[2] + " - " + movieDetails[1]);
        details.setText(movieDetails[4]);
        details2.setText(movieDetails[0]);
        details3.setText(movieDetails[1]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"Item1 selected",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}