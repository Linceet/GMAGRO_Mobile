package com.ageneste.gmagro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.ageneste.gmagro.dao.DaoIntervenant;

public class CreateIntervention extends AppCompatActivity {
    private TextView tv_qui;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_intervention);
        tv_qui = findViewById(R.id.tv_qui);
        tv_qui.setText("Vous étes connecter en tent que "+ DaoIntervenant.getInstance(this).getMoi().toString());


    }
}