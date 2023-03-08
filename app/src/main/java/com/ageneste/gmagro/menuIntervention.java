package com.ageneste.gmagro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ageneste.gmagro.dao.DaoIntervenant;
import com.ageneste.gmagro.dao.DaoIntervention;
import com.ageneste.gmagro.dao.DelegateAsyncTask;

public class menuIntervention extends AppCompatActivity {
    private TextView tv_qui;
    private ArrayAdapter aaInterv;
    private ListView lvInterv;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_intervention);
        tv_qui = findViewById(R.id.tv_person_conne);
        tv_qui.setText("Vous étes connecter en tent que "+ DaoIntervenant.getInstance(this).getMoi().toString());
        findViewById(R.id.btn_deco).setOnClickListener(this::clicBtnDeco);
        findViewById(R.id.btn_nvx_Interv).setOnClickListener(this::clicBtnNvxInterv);
        lvInterv = findViewById(R.id.lv_interv);
        getIntervs();

    }

    private void getIntervs() {
        DaoIntervention.getInstance(this).getIntervention(r ->{
         if((Integer) r == 1){
             aaInterv = new ArrayAdapter<>(menuIntervention.this, android.R.layout.simple_list_item_1, DaoIntervention.getInstance(menuIntervention.this).getIntervs());
             lvInterv.setAdapter(aaInterv);
         }
        });
    }

    private void clicBtnNvxInterv(View v) {
        Intent inting = new Intent(this, CreateIntervention.class);
        startActivityForResult(inting, 0);

    }

    private void clicBtnDeco(View v){
        DaoIntervenant.getInstance(this).deco(new DelegateAsyncTask() {
            @Override
            public void whenWSConnexionIsTerminated(Object result) {
                if (result.equals(1)){
                    setResult(1);
                    finish();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0){
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        } else if (resultCode == 1) {
            Toast.makeText(this, "Intervention créée!", Toast.LENGTH_SHORT).show();
            aaInterv.notifyDataSetChanged();
        }

    }

}