package com.example.imccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;

public class Informacoes extends AppCompatActivity {
    private static final String TAG = "Informacoes";
    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);

        mDisplayDate = (EditText) findViewById(R.id.DataNasc);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = null; new DatePickerDialog(Informacoes.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                    dialog.show();


            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month= month + 1;
                Log.d(TAG, "onDateSet: dd/mm/aaaa:" +dayOfMonth+ "/" + month+ "/" +year);
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }
}
