
package com.pamiranindustries.pamiran;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    private Button button0;

    private String[] myStringArray={"Movies","Service 2","Service 3","Service 4","Service 5","Service 6"};
    //    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        BtnAdapter adapter= new BtnAdapter(MainActivity.this,myStringArray);

        gridView.setAdapter(new BtnAdapter(this,myStringArray));
        gridView.setAdapter(adapter);

//        Toolbar toolbar = (Toolbar) findViewById((R.id.toolbar));
//        setSupportActionBar(toolbar);
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
//        startActivity(new Intent(MainActivity.this,MoviesActivity.class));\
        Toast.makeText(MainActivity.this,"will work soon!",Toast.LENGTH_LONG).show();
    }
    //    public void makeToast(View view) {
//        Button btn = (Button) view;
//
//        Toast.makeText(MainActivity.this,"You selected "+btn.getText(),Toast.LENGTH_LONG).show();
//    }
    //passing the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"Item1 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this,"Item2 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this,"Item3 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Toast.makeText(this,"SubItem1 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Toast.makeText(this,"SubItem2 selected",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class BtnOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            if (btn.getText() == "Movies") {

                startActivity(new Intent(MainActivity.this,MoviesActivity.class));

            } else {
                Toast.makeText(MainActivity.this,"You selected "+btn.getText(),Toast.LENGTH_LONG).show();
            }
        }
    }




    public class BtnAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private String [] myStringArray;
        private int position;

        public BtnAdapter(Context c, String[] myStringArray){
            context = c;
            this.myStringArray = myStringArray;

        }
        @Override
        public int getCount() {
            return myStringArray.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Button btn;
            if (convertView == null){
                btn = new Button(context);
            } else {
                btn = (Button) convertView;
            }
            btn.setText(myStringArray[position]);
            btn.setId(position);
            btn.setOnClickListener(new BtnOnClickListener());
            return btn;
        }

    }

}


