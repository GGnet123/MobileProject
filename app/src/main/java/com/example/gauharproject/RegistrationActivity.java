package com.example.gauharproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gauharproject.db.User;
import com.example.gauharproject.db.UserDb;
import com.example.gauharproject.retrofit.JSONPlaceHolderApi;
import com.example.gauharproject.retrofit.NetworkClient;
import com.example.gauharproject.retrofit.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity {

    String token = null;
    ProgressDialog pDialog;
    String name, pass,repeat_pass;
    UserDb userDB;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
    public void register(View view){
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final EditText repeat_password = findViewById(R.id.repeat_password);
        name = username.getText().toString();
        pass = password.getText().toString();
        repeat_pass = repeat_password.getText().toString();

        if (name.replace(" ","").equals("")|| pass.replace(" ","").equals("")){
            Toast.makeText(RegistrationActivity.this, "Something is missing", Toast.LENGTH_SHORT).show();
        } else {
            if (pass.equals(repeat_pass))
                new loginAction().execute();
            else
                Toast.makeText(RegistrationActivity.this, "Passwords doesn't match", Toast.LENGTH_LONG).show();
        }
    }
    private class loginAction extends AsyncTask<Void, Void, Void> {
        @Override
        public void onPreExecute(){

            final EditText username = findViewById(R.id.username);
            final EditText password = findViewById(R.id.password);

            name = username.getText().toString();
            pass = password.getText().toString();

            pDialog = new ProgressDialog(
                    RegistrationActivity.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        public Void doInBackground(Void...unused){

            Retrofit retrofit = NetworkClient.getRetrofitClient();
            JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);

            Post post = new Post(name, pass);

            Call<Post> call = jp.register(post);

            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess()){
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            pDialog.cancel();
                            Toast.makeText(RegistrationActivity.this, "Login is already in use", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.i("msg",response.toString());
                        pDialog.cancel();
                        Toast.makeText(RegistrationActivity.this, "Login is already in use", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Log.i("error", t.getMessage());
                }
            });
            return null;
        }
        protected void onPostExecute(Void unused) {
            // closing progress dialog
            pDialog.dismiss();
        }
    }
}
