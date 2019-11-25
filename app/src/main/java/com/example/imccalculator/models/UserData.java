package com.example.imccalculator.models;

import java.util.Calendar;

public class UserData {

    private String nome;
    private float altura;
    private int anoNascimento;
    private boolean isHomem;

    public UserData(String nome, float altura, int anoNascimento, boolean isHomem){
        this.nome = nome;
        this.altura = altura;
        this.anoNascimento = anoNascimento;
        this.isHomem = isHomem;
    }
    public int getIdade(){
        Calendar calendar = Calendar.getInstance();
        int anoAtual = calendar.get(Calendar.YEAR);
        int idade = anoAtual - anoNascimento;
        return idade;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public int getDataNascimento() {
        return anoNascimento;
    }

    public void setDataNascimento(int dataNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public boolean isHomem() {
        return isHomem;
    }

    public void setHomem(boolean homem) {
        isHomem = homem;
    }
}
