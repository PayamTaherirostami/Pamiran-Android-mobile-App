package com.pamiranindustries.pamiran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    private Button button0;

    String [] myStringArray={"Service 1","Service 2","Service 3","Service 4","Service 5","Service 6"};
//    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        BtnAdapter adapter= new BtnAdapter(MainActivity.this,myStringArray);

        gridView.setAdapter(new BtnAdapter(this,myStringArray));
        gridView.setAdapter(adapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//      String selectedItem = (String) parent.getItemAtPosition(position);
//
//                Toast.makeText(MainActivity.this,"You selected "+ selectedItem,Toast.LENGTH_LONG).show();
//            }
//        });
//
  }
    public void OtherActivity(View view) {
        startActivity(new Intent(MainActivity.this,MainActivity2.class));
    }
    public void makeToast(View view) {
        Button btn = (Button) view;
  
        Toast.makeText(MainActivity.this,"You selected "+btn.getText(),Toast.LENGTH_LONG).show();
    }
}