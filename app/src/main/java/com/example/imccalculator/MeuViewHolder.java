package com.example.imccalculator;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MeuViewHolder extends RecyclerView.ViewHolder {
    final TextView data;
    final TextView imc;
    final TextView manter;
    final TextView emagrecer;
    final TextView engordar;

    public MeuViewHolder(View view){
        super(view);
        data = (TextView) view.findViewById(R.id.calculo_data);
        imc = (TextView) view.findViewById(R.id.calculo_imc);
        emagrecer = (TextView) view.findViewById(R.id.calculo_calorias_emagrecer);
        engordar = (TextView) view.findViewById(R.id.calculo_calorias_engordar);
        manter = (TextView) view.findViewById(R.id.calculo_calorias_manter);
    }
}
