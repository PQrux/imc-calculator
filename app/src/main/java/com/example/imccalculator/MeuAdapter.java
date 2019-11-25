package com.example.imccalculator;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.imccalculator.models.Historico;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MeuAdapter extends RecyclerView.Adapter {
    private ArrayList<Historico> lista;
    private Context context;

    public MeuAdapter(Context context, ArrayList<Historico> lista) {
        this.lista = lista;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_text_view,parent, false);
        MeuViewHolder holder = new MeuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MeuViewHolder holder = (MeuViewHolder) viewHolder;
        Historico historico = lista.get(position);
        holder.data.setText(historico.getDataFormatada());
        holder.imc.setText(historico.getImcText());
        holder.manter.setText(Integer.toString(historico.getTmb()));
        holder.emagrecer.setText(Integer.toString(historico.getEmagrecer()));
        holder.engordar.setText(Integer.toString(historico.getEngordar()));
    }
    @Override public int getItemCount() {
        return lista.size();
    }

}