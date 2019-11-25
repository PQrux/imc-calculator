package com.example.imccalculator.models;

import android.graphics.Color;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Historico {
    private UserData userData;
    private float peso;
    private Calendar data;

    public Historico(UserData userData, float peso, long timestamp){
        this.userData = userData;
        this.peso = peso;
        this.data = Calendar.getInstance();
        this.data.setTimeInMillis(timestamp);
    }
    public String getDataFormatada(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(data.getTime());
    }
    public int getEmagrecer(){
        return (int) (getTmb() * 0.8f);
    }
    public int getEngordar(){
        return (int) (getTmb() * 1.2f);
    }
    public int getTmb(){
        float tmb = 0;
        if(userData.isHomem()){
            tmb = 66+(13.7f * peso) + (5 * userData.getAltura()) - (6.8f * userData.getIdade());
        }
        else{
            tmb = 665 + (9.6f * peso) + (1.8f * userData.getAltura()) - (4.7f * userData.getIdade());
        }
        return (int) tmb;
    }

    public float getImc(){
        float imc = peso / (userData.getAltura() * userData.getAltura());
        return imc;
    }
    public String getImcText(){
        float imc = getImc();
        String result = String.format("%.2f - ", imc);
        if(imc < 18.5f){
            result+="Magreza";
        }
        else if(imc < 24.9f){
            result+="Normal";
        }
        else if(imc < 29.9f){
            result+="Sobrepeso";
        }
        else if(imc < 39.9f){
            result+="Obesidade";
        }
        else{
            result+="Obesidade Grave";
        }
        return result;
    }
    public int getImcColor(){
        float imc = getImc();
        int result = Color.BLACK;
        if(imc < 18.5f){
            result = Color.BLUE;
        }
        else if(imc < 24.9f){
            result = Color.GREEN;
        }
        else if(imc < 29.9f){
            result = Color.rgb(245, 209, 66);
        }
        else if(imc < 39.9f){
            result = Color.rgb(245, 126, 66);
        }
        else{
            result = Color.RED;
        }
        return result;
    }
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    public void setData(long millis){
        this.data.setTimeInMillis(millis);
    }
    public long getDataAsMillis() {
        return data.getTimeInMillis();
    }
}
