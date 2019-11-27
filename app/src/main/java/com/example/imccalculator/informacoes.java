package com.example.imccalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.imccalculator.models.UserData;

public class informacoes extends AppCompatActivity implements View.OnClickListener {
    private EditText nome;
    private EditText dataNasc;
    private EditText altura;
    private RadioGroup sexo;
    private Button prosseguir;
    private ContentController contentController;
    private boolean usuarioNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);
        getSupportActionBar().hide();
        this.contentController = new ContentController(this);
        UserData userData = contentController.getUserData();
        if(userData != null){
            usuarioNovo = false;
            nome.setText(userData.getNome());
            dataNasc.setText(Integer.toString(userData.getDataNascimento()));
            altura.setText(Float.toString(userData.getAltura()));
            if(userData.isHomem()){
                sexo.check(R.id.masculino);
            }
            else{
                usuarioNovo = true;
                sexo.check(R.id.feminino);
            }
        }
        nome = (EditText) findViewById(R.id.nome);
        dataNasc = (EditText) findViewById(R.id.DataNasc);
        altura = (EditText) findViewById(R.id.altura);
        sexo = (RadioGroup) findViewById(R.id.sexo);
        prosseguir = (Button) findViewById(R.id.prosseguir);

        prosseguir.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        try{
            boolean isHomem;
            if(sexo.getCheckedRadioButtonId() == R.id.masculino){
                isHomem = true;
            }
            else{
                isHomem = false;
            }

            UserData userData = new UserData(
                nome.getText().toString(),
                Float.parseFloat(altura.getText().toString()),
                Integer.parseInt(dataNasc.getText().toString()),
                isHomem
            );
            contentController.saveUserData(userData);
            if(usuarioNovo){
                Intent i = new Intent(this, DescricaoIMC.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
            else{
                Intent i = new Intent(this, Home.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }
        catch (NumberFormatException|NullPointerException e){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Todos os campos devem ser preenchidos!");
        }
        catch (Exception e){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Ocorreu um erro ao realizar o salvamento, tente novamente.");
        }
    }
}
