
package com.pamiranindustries.pamiran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonLogin;
    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword, editTextUserName;
    ProgressBar progressBar;
    private EditText username;
    private String[] myStringArray={"Movies","Cameras","Map","Service 4","Service 5","Service 6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mAuth = FirebaseAuth.getInstance();
        GridView gridView = (GridView) findViewById(R.id.gridView);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName= (EditText) findViewById(R.id.editTextUserName);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        buttonLogin= (Button) findViewById(R.id.buttonLogin);

        BtnAdapter adapter= new BtnAdapter(MainActivity.this,myStringArray);
        gridView.setAdapter(new BtnAdapter(this,myStringArray));
        gridView.setAdapter(adapter);

        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);


    }


    public void OtherActivity(View view) {
//        startActivity(new Intent(MainActivity.this,MoviesActivity.class));\
//        Toast.makeText(MainActivity.this,"will work soon!",Toast.LENGTH_LONG).show();
//        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
//        mBuilder.setIcon(android.R.drawable.sym_def_app_icon);
//        mBuilder.setTitle("About us");
//        mBuilder.setMessage("Hello this is us");
//        mBuilder.setCancelable(false);
//        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                dialogInterface.dismiss();
//            }
//        });
//        mBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                Toast.makeText(MainActivity.this,"will work soon!",Toast.LENGTH_LONG).show();
//            }
//        });
//        mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity(new Intent(MainActivity.this,MoviesActivity.class));
//            }
//        });
//        AlertDialog alertDialog = mBuilder.create();
//        alertDialog.show();
    }

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

                startActivity(new Intent(MainActivity.this,MoviesActivity.class));}
            else if (btn.getText() =="Cameras") {
                startActivity(new Intent(MainActivity.this, CamerasActivity.class));
            }else if (btn.getText() =="Map") {
                    startActivity(new Intent(MainActivity.this,TrafficMapActivity.class));
                }

             else {
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
//    private void userLogin() {
    private void signIn(){

        Log.d("FIREBASE", "signIn");
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String username = editTextUserName.getText().toString().trim();
        Log.d("FIREBASE", "email:" + email);
        Log.d("FIREBASE", "password:" + password);
//        if (username.isEmpty()) {
//            editTextUserName.setError("UserName is required");
//            editTextUserName.requestFocus();
//            return;
//        }
//
//        if (email.isEmpty()) {
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            editTextEmail.setError("Please enter a valid email");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (password.isEmpty()) {
//            editTextPassword.setError("Password is required");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        if (password.length() < 6) {
//            editTextPassword.setError("Minimum lenght of password should be 6");
//            editTextPassword.requestFocus();
//            return;
//        }

//        progressBar.setVisibility(View.VISIBLE);

//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                progressBar.setVisibility(View.GONE);
                Log.d("FIREBASE", "signIn:onComplete:" + task.isSuccessful());

                if (task.isSuccessful()) {
                    // update profile
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("FIREBASE", "User profile updated.");
                                        // Go to FirebaseActivity
                                        startActivity(new Intent(MainActivity.this, MoviesActivity.class));
                                    }
                                }
                            });

                } else {
                    Log.d("FIREBASE", "sign-in failed");

                    Toast.makeText(MainActivity.this, "Sign In Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//                if (task.isSuccessful()) {
//                    finish();
//                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
   @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.textViewSignup:
//                finish();
//                startActivity(new Intent(this, SignUpActivity.class));
//                break;

            case R.id.buttonLogin:
//                userLogin();
                signIn();
                break;
        }
    }

}


