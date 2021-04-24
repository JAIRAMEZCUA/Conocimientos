package com.example.conocimientos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.Window;

import com.example.conocimientos.databinding.ActivityMainBinding;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       //Llama a CallbackManager.Factory.create para crear un administrador de devoluciones de llamada que gestione las respuestas de inicio de sesión.
       callbackManager = CallbackManager.Factory.create();
       binding.loginButton.setPermissions(Arrays.asList("email","public_profile"));

       binding.loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
           @Override
           public void onSuccess(LoginResult loginResult) {

           }

           @Override
           public void onCancel() {

           }

           @Override
           public void onError(FacebookException error) {

           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker accessToken = new AccessTokenTracker(){

        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

        }
    };

    private  void loaduserProfile (AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try{
                    String firsName = object.getString("firts_name");
                    String lastName = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String image = "https://graph.facebook.com/"+id+"/picture?type=normal";


                }catch (Exception e){
                    Log.d(TAG,""+e);
                }
            }
        });
    }
//    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//    LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile"));

}