package com.example.gauharproject;

import androidx.annotation.NonNull;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {
    String token = null;
    ProgressDialog pDialog;
    String name, pass;
    UserDb userDB;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDB = UserDb.getInstance(this);
        new autoLogin().execute();
    }

    private class autoLogin extends AsyncTask<Void, Void, Void>{
        @Override
        public void onPreExecute(){
            pDialog = new ProgressDialog(
                    LoginActivity.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        public Void doInBackground(Void...unused){
            if (userDB.getUserDao().getUsers().isEmpty()){
                pDialog.cancel();
                this.cancel(true);
            }

            List<User> users = userDB.getUserDao().getUsers();

            name = users.get(0).getLogin();
            pass = users.get(0).getPassword();

            Retrofit retrofit = NetworkClient.getRetrofitClient();
            JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);
            Post post = new Post(name, pass);
            Call<Post> call = jp.login(post);

            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        Post postResponce = response.body();
                        if (postResponce.getSuccess()){
                            token = postResponce.getToken();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("token", token);
                            startActivity(intent);
                            finish();
                        } else {
                            pDialog.cancel();
                            Toast.makeText(LoginActivity.this, "Invalid login/password!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.i("msg",response.toString());
                        pDialog.cancel();
                        Toast.makeText(LoginActivity.this, "Invalid login/password!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Log.i("error", t.getMessage());
                    Log.d("here","here");
                }
            });
            return null;
        }
    }

    public void register(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        new loginAction().execute();
    }
    private class loginAction extends AsyncTask<Void, Void, Void> {
        @Override
        public void onPreExecute(){

            final EditText username = findViewById(R.id.username);
            final EditText password = findViewById(R.id.password);

            name = username.getText().toString();
            pass = password.getText().toString();

            pDialog = new ProgressDialog(
                    LoginActivity.this);
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        public Void doInBackground(Void...unused){

            Retrofit retrofit = NetworkClient.getRetrofitClient();
            JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);

            Post post = new Post(name, pass);
            user = new User(1,name,pass);

            userDB.getUserDao().insert(user);
            Call<Post> call = jp.login(post);

            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        Post postResponce = response.body();
                        if (postResponce.getSuccess()){
                            token = postResponce.getToken();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("token", token);
                            startActivity(intent);
                            finish();
                        } else {
                            pDialog.cancel();
                            Toast.makeText(LoginActivity.this, "Invalid login/password!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.i("msg",response.toString());
                        pDialog.cancel();
                        Toast.makeText(LoginActivity.this, "Invalid login/password!", Toast.LENGTH_SHORT).show();
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
