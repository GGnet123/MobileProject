package com.example.gauharproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.gauharproject.db.UserDb;
import com.example.gauharproject.retrofit.JSONPlaceHolderApi;
import com.example.gauharproject.retrofit.NetworkClient;
import com.example.gauharproject.retrofit.Post;
import com.example.gauharproject.retrofit.User;
import com.example.gauharproject.ui.profile.ProfileFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public ProgressDialog pDialog;
    int user_id;
    String name;
    String surname;
    String age;
    String description;
    ArrayList<String> favourite;
    Bundle data;
    EditText edit_name;
    EditText edit_surname;
    EditText edit_age;
    EditText edit_description;
    TextView profile_name;
    TextView profile_surname;
    TextView profile_age;
    TextView profile_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_id = getIntent().getIntExtra("user_id", 0);
        name = getIntent().getStringExtra("name");
        surname = getIntent().getStringExtra("surname");
        age = getIntent().getStringExtra("age");
        description = getIntent().getStringExtra("description");
        favourite = getIntent().getStringArrayListExtra("favourite");

        data = new Bundle();
        data.putInt("user_id", user_id);
        data.putString("name", name);
        data.putString("surname", surname);
        data.putString("age", age);
        data.putString("description", description);
        data.putStringArrayList("favourite", favourite);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile, R.id.nav_gallery, R.id.nav_categories,
                R.id.nav_notes, R.id.nav_favourite, R.id.nav_settings, R.id.nav_popular)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.user);
        navUsername.setText("Welcome, my dear " + name);

    }

    public Bundle getData(){
        return data;
    }

    public void setData(String name, String surname, int age, String description){
        data.putString("name", name);
        data.putString("surname", surname);
        data.putString("description", description);
        data.putInt("age", age);
    }

    public void Edit(View view){
        Button btn = findViewById(R.id.edit_btn);

        if (btn.getText().toString().equals("save")){
            setData(edit_name.getText().toString(), edit_surname.getText().toString(), Integer.parseInt(edit_age.getText().toString()), edit_description.getText().toString());

            Retrofit retrofit = NetworkClient.getRetrofitClient();
            JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);

            User user = new User(data.getString("name"), data.getString("surname"), data.getInt("age"), data.getString("description"));

            Call<User> call = jp.editProfile(data.getInt("user_id"), user);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                        Log.d("succ", "true");
                        profile_name.setText(data.getString("name"));
                        profile_surname.setText(data.getString("surname"));
                        profile_age.setText(data.getInt("age")+"");
                        profile_description.setText(data.getString("description"));
                        ChangeView();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        } else {
            ChangeView();
        }

        if (btn.getText().toString().equals("save")) {
            btn.setText("edit");
        } else {
            btn.setText("save");
        }
    }

    private void ChangeView(){
        profile_name = findViewById(R.id.profile_name);
        profile_surname = findViewById(R.id.profile_surname);
        profile_age = findViewById(R.id.profile_age);
        profile_description = findViewById(R.id.profile_description);

        edit_name = findViewById(R.id.edit_name);
        edit_surname = findViewById(R.id.edit_surname);
        edit_description = findViewById(R.id.edit_description);
        edit_age = findViewById(R.id.edit_age);

        int visibilityTextView = edit_name.getVisibility();
        int visibilityEditView = profile_name.getVisibility();

        Log.d("textview", visibilityTextView+"");
        Log.d("editview", visibilityEditView+"");

        profile_name.setVisibility(visibilityTextView);
        profile_surname.setVisibility(visibilityTextView);
        profile_description.setVisibility(visibilityTextView);
        profile_age.setVisibility(visibilityTextView);

        edit_name.setVisibility(visibilityEditView);
        edit_description.setVisibility(visibilityEditView);
        edit_surname.setVisibility(visibilityEditView);
        edit_age.setVisibility(visibilityEditView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void exit(View view){
        new logOut().execute();
    }

    private class logOut extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(
                    MainActivity.this);
            pDialog.setMessage("Подождите..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDb db = UserDb.getInstance(MainActivity.this);
            db.getUserDao().clearDb();
            Intent in = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(in);
            finish();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.cancel();
        }
    }
}
