package com.pamiranindustries.pamiran;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        TextView details = findViewById(R.id.movieDet);
        TextView details2 = findViewById(R.id.movieTit);
        TextView details3 = findViewById(R.id.Date);
        TextView details4 = findViewById(R.id.movieLink);
        ImageView details5 = findViewById(R.id.image);
        TextView details6 = findViewById(R.id.Director);

        String[] movieDetails = getIntent().getStringArrayExtra("ExtraDetail");


        details2.setText(movieDetails[0]);
        details3.setText(movieDetails[1]);
        Picasso.get().load(movieDetails[3]).into(details5);
        details4.setText(movieDetails[3]);
        details.setText(movieDetails[4]);
        details6.setText(movieDetails[2]);


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