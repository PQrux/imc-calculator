package com.example.imccalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.imccalculator.models.UserData;
import com.google.gson.Gson;

public class ContentController {
    private Context context;
    private SharedPreferences root;
    private Gson gson;

    public ContentController(Context context){
        this.context = context;
        root = context.getSharedPreferences("root", Context.MODE_PRIVATE);
        this.gson = new Gson();
    }
    public void saveUserData(UserData userData){
        SharedPreferences.Editor editor = root.edit();
        String json = gson.toJson(userData);
        editor.putString("userData", json);
        editor.apply();
    }
    public UserData getUserData(){
        String json = root.getString("userData", "");
        Log.i("tasd", "asdasd"+json);
        UserData userData = (UserData) gson.fromJson(json, UserData.class);
        return userData;
    }
}
