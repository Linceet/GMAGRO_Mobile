package com.ageneste.gmagro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ageneste.gmagro.dao.DaoIntervenant;
import com.ageneste.gmagro.dao.DaoIntervention;
import com.ageneste.gmagro.dao.DelegateAsyncTask;

public class CreateIntervention extends AppCompatActivity {
    private Spinner sp_act;
    private ArrayAdapter aAct;
    private EditText heureDeb;
    private  Spinner sp_mach;
    private ArrayAdapter aMach;
    private Spinner sp_cd;
    private ArrayAdapter aCd;
    private Spinner sp_co;
    private ArrayAdapter aCo;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_intervention);
        TextView tv_qui = findViewById(R.id.tv_qui);
        tv_qui.setText("Vous étes connecter en tent que "+ DaoIntervenant.getInstance(this).getMoi().toString());
        sp_act = findViewById(R.id.sp_act);
        getAct();
        heureDeb = findViewById(R.id.tt_heurDeb);
        sp_mach = findViewById(R.id.sp_machine);
        getMach();
        sp_cd = findViewById(R.id.sp_cd);
        getCd();
        sp_co = findViewById(R.id.sp_co);
        getCo();


    }

    private void getCo() {
        int codeE = DaoIntervenant.getInstance(this).getMoi().getCodeEtab();
        DaoIntervention.getInstance(this).getCo(codeE, new DelegateAsyncTask() {
            @Override
            public void whenWSConnexionIsTerminated(Object result) {
                if(result.equals(1)) {
                    aCo = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getCds());
                    sp_co.setAdapter(aCo);
                }else{
                    Toast.makeText(CreateIntervention.this, "Erreur de chargement des machines !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getCd() {
        int codeE = DaoIntervenant.getInstance(this).getMoi().getCodeEtab();
        DaoIntervention.getInstance(this).getCd(codeE, new DelegateAsyncTask() {
            @Override
            public void whenWSConnexionIsTerminated(Object result) {
                if(result.equals(1)) {
                    aCd = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getCds());
                    sp_cd.setAdapter(aCd);
                }else{
                    Toast.makeText(CreateIntervention.this, "Erreur de chargement des machines !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getMach() {
        int codeE = DaoIntervenant.getInstance(this).getMoi().getCodeEtab();
        DaoIntervention.getInstance(this).getMach(codeE, new DelegateAsyncTask() {
            @Override
            public void whenWSConnexionIsTerminated(Object result) {
                if(result.equals(1)) {
                    aMach = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getMachines());
                    sp_mach.setAdapter(aMach);
                }else{
                    Toast.makeText(CreateIntervention.this, "Erreur de chargement des machines !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getAct() {
        DaoIntervention.getInstance(this).getActi(new DelegateAsyncTask() {
            @Override
            public void whenWSConnexionIsTerminated(Object result) {
                if(result.equals(1)){
                    aAct = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getActivites());
                    sp_act.setAdapter(aAct);
                }else{
                    Toast.makeText(CreateIntervention.this, "Erreur de chargement des Activité !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}