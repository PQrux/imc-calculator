package com.example.imccalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.example.imccalculator.models.Historico;

import com.example.imccalculator.models.UserData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Home extends AppCompatActivity {

    private Button calcular;
    private RecyclerView lista;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager layoutManager;
    private UserData userData;
    private ContentController contentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userData = new UserData("Joao", 1.60f, 1995,true);
        contentController = new ContentController(this);
        lista = findViewById(R.id.lista);
        calcular = findViewById(R.id.calcular);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        lista.setLayoutManager(layoutManager);

        ArrayList<Historico> historicos = preencherDados();
        lista.setAdapter(new MeuAdapter(this, historicos));
    }
    private ArrayList<Historico> preencherDados(){
        ArrayList<Historico> dados = new ArrayList<Historico>();
        long currentDate = Calendar.getInstance().getTimeInMillis();
        dados.add(new Historico(userData, 80.6f, currentDate));
        dados.add(new Historico(userData, 60.6f, currentDate));
        return dados;
    }
}
