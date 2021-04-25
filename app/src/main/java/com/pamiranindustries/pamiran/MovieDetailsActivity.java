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

        // Declaring view for population
        TextView details = findViewById(R.id.movieDet);
//        ImageView imageView = findViewById(R.id.movieImDet);

        // Getting the details from the previous activity and populating the text view with them
        String[] movieDetails = getIntent().getStringArrayExtra("EXTRA_MOVIE_DETAILS");

        // Setting the given info in the activity
        Objects.requireNonNull(getSupportActionBar()).setTitle(movieDetails[0]);
        getSupportActionBar().setSubtitle(movieDetails[2] + " - " + movieDetails[1]);
//        Picasso.get().load(movieDetails[3]).into(imageView);
        details.setText(movieDetails[4]);
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
//            case R.id.item2:
//                Toast.makeText(this,"Item2 selected",Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.item3:
//                Toast.makeText(this,"Item3 selected",Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.subitem1:
//                Toast.makeText(this,"SubItem1 selected",Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.subitem2:
//                Toast.makeText(this,"SubItem2 selected",Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//
//    /**
//     * MENU
//     * EventHandler for all the options in the menu
//     */
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.fire:
//                Toast.makeText(this, "Fire Baby!", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.about:
//                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
