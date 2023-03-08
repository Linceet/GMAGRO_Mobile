package com.ageneste.gmagro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ageneste.gmagro.Beans.Activite;
import com.ageneste.gmagro.Beans.Cd;
import com.ageneste.gmagro.Beans.Co;
import com.ageneste.gmagro.Beans.Intervenant;
import com.ageneste.gmagro.Beans.IntervenantTps;
import com.ageneste.gmagro.Beans.Machine;
import com.ageneste.gmagro.Beans.Sd;
import com.ageneste.gmagro.Beans.So;
import com.ageneste.gmagro.dao.DaoIntervenant;
import com.ageneste.gmagro.dao.DaoIntervention;

import java.util.ArrayList;
import java.util.List;

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
    private Spinner sp_sd;
    private ArrayAdapter aSd;
    private Spinner sp_so;
    private ArrayAdapter aSo;
    private Spinner sp_aInter;
    private ArrayAdapter aInter;
    private final List<String> time = new ArrayList<>();
    private  Spinner sp_aTpsPass;
    private ArrayAdapter aTpsPass;
    private CheckBox cbFin;
    private CheckBox cbArr;
    private CheckBox cbCho;
    private CheckBox cbPert;
    private EditText tm_Fin;
    private Spinner sp_tpsArr;
    private ArrayAdapter aTpsArr;
    private Button btn_addInters;
    private ListView lv_Interv;
    private ArrayAdapter aLvInters;
    private final List<IntervenantTps> lInters = new ArrayList<>();
    private Button btn_addIntervention;
    private EditText et_Comm;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_intervention);
        TextView tv_qui = findViewById(R.id.tv_qui);
        tv_qui.setText("Vous étes connecter en tent que "+ DaoIntervenant.getInstance(this).getMoi().toString());
        et_Comm = findViewById(R.id.et_desc);
        cbCho = findViewById(R.id.cb_changOrga);
        cbPert = findViewById(R.id.cb_perte);
        sp_act = findViewById(R.id.sp_act);
        getAct();
        sp_mach = findViewById(R.id.sp_machine);
        getMach();
        sp_cd = findViewById(R.id.sp_cd);
        getCd();
        sp_co = findViewById(R.id.sp_co);
        getCo();
        sp_sd = findViewById(R.id.sp_sd);
        getSd();
        sp_so = findViewById(R.id.sp_so);
        getSo();
        sp_aInter = findViewById(R.id.sp_intervenant);
        getInterv();
        sp_aTpsPass = findViewById(R.id.sp_temps);
        getTimePass();
        cbFin = findViewById(R.id.cb_termi);
        tm_Fin = findViewById(R.id.tm_Fin);
        tm_Fin.setVisibility(View.INVISIBLE);
        cbFin.setOnClickListener(view -> {
            if(cbFin.isChecked()){
             tm_Fin.setVisibility(View.VISIBLE);
             }else{
                 tm_Fin.setVisibility(View.INVISIBLE);
             }
        });
        cbArr = findViewById(R.id.cb_machArr);
        sp_tpsArr = findViewById(R.id.sp_tpsArr);
        getTimeArr();
        sp_tpsArr.setVisibility(View.INVISIBLE);
        cbArr.setOnClickListener(view -> {
            if(cbArr.isChecked()){
                sp_tpsArr.setVisibility(View.VISIBLE);
            }else{
                sp_tpsArr.setVisibility(View.INVISIBLE);
            }
        });
        btn_addInters = findViewById(R.id.btn_ajouterIntervenant);
        lv_Interv = findViewById(R.id.lv_intervenant);
        btn_addInters.setOnClickListener(view -> {
            onClicBtnAddInterv();
            aLvInters.notifyDataSetChanged();
            aInter.notifyDataSetChanged();
        });
        lv_Interv.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Log.d("LONGCLICK","On est pas encore passé") ;
            onLongClicOnLvInters(i);
            Log.d("LONGCLICK","On est passé") ;
            return true;
        });
        btn_addIntervention = findViewById(R.id.btn_ajouterIntervention);
        btn_addIntervention.setOnClickListener(view -> onClicbtnAddIntervens());

    }

    private void onLongClicOnLvInters(int i) {
        DaoIntervenant.getInstance(this).addIntervFromLV((IntervenantTps)lv_Interv.getItemAtPosition(i));
        lInters.remove((IntervenantTps)lv_Interv.getItemAtPosition(i));
        aLvInters.notifyDataSetChanged();
        aInter.notifyDataSetChanged();
    }

    private void onClicbtnAddIntervens() {
        String cAct = ((Activite)sp_act.getSelectedItem()).getCode();
        String cMach = ((Machine)sp_mach.getSelectedItem()).getCodeMachine();
        String cCd = ((Cd)sp_cd.getSelectedItem()).getCodeCD();
        String cCo = ((Co)sp_co.getSelectedItem()).getCodeCo();
        String cSd = ((Sd)sp_sd.getSelectedItem()).getCodeSd();
        String cSo = ((So)sp_so.getSelectedItem()).getCodeSo();
        String cCom = et_Comm.getText().toString();
        int cChO;
        int cPer;
        String cTpsArr = "null";
        String cTpsPass = "null";
        if(cbFin.isChecked()) {
            cTpsPass = tm_Fin.getText().toString();
        }else{
            cTpsPass = "null";
        }
        if(cbArr.isChecked()) {
            cTpsArr = sp_tpsArr.getSelectedItem().toString();
        }else{
            cTpsArr = "null";
        }
        if(cbCho.isChecked()){
            cChO = 1;
        }else{
            cChO = 0;
        }
        if(cbPert.isChecked()){
            cPer = 1;
        }else{
            cPer = 0;
        }
        String cMsg = "";
        for(IntervenantTps i : lInters){
            cMsg = cMsg + i.getIntervenant().getMail() + "|" + i.getTps()+"||";
        }

        DaoIntervention.getInstance(this).addIntervention(cAct,cMach,cCd,cCo,cSd,cSo,cCom,cChO,cPer,cTpsPass,cTpsArr,cMsg,result -> {

                setResult(1);
                finish();
        });


    }

    private void onClicBtnAddInterv() {
        IntervenantTps iTps = new IntervenantTps((Intervenant) sp_aInter.getSelectedItem(),sp_aTpsPass.getSelectedItem().toString());
        lInters.add(iTps);
        aLvInters = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_list_item_1, lInters);
        lv_Interv.setAdapter(aLvInters);
        DaoIntervenant.getInstance(this).removeIntervFromList((Intervenant) sp_aInter.getSelectedItem());
        Toast.makeText(CreateIntervention.this, "Ajout de l'Intervenant!", Toast.LENGTH_SHORT).show();
    }

    private void getCo() {
        DaoIntervention.getInstance(this).getCo(result -> {
            if(result.equals(1)) {
                aCo = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getCos());
                sp_co.setAdapter(aCo);
            }else{
                Toast.makeText(CreateIntervention.this, "Erreur de chargement des CauseObject !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCd() {
        DaoIntervention.getInstance(this).getCd(result -> {
            if(result.equals(1)) {
                aCd = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getCds());
                sp_cd.setAdapter(aCd);
            }else{
                Toast.makeText(CreateIntervention.this, "Erreur de chargement des CauseDefault !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSd(){
        DaoIntervention.getInstance(this).getSd(result -> {
            if(result.equals(1)) {
                aSd = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getSds());
                sp_sd.setAdapter(aSd);
            }else{
                Toast.makeText(CreateIntervention.this, "Erreur de chargement des SymptomeDefault !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSo(){
        DaoIntervention.getInstance(this).getSo(result -> {
            if(result.equals(1)){
                aSo = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getSos());
                sp_so.setAdapter(aSo);
            }else{
                Toast.makeText(CreateIntervention.this, "Erreur de chargement des SymptomeObject !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getInterv(){
        DaoIntervenant.getInstance(this).getAllPersonne(result -> {
            if(result.equals(1)){
                aInter = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervenant.getInstance(CreateIntervention.this).getIntervs());
                sp_aInter.setAdapter(aInter);
            }else{
                Toast.makeText(CreateIntervention.this, "Erreur de chargement des Intervenants !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListTime(){
        time.clear();
        int i = 0 ;
        int min = 00;
        while(i<=7) {
            min = min+15;
            if(min == 60){
                i = i+1;
                min = 0;
            }
            String temps;
            if(String.valueOf(min).length()==1){
                temps = "0" + String.valueOf(i)+ ":"+ String.valueOf(min)+"0";
            }else{
                temps = "0" + String.valueOf(i)+ ":"+ String.valueOf(min);
            }

            time.add(temps);
        }
    }

    private  void getTimePass(){
        setListTime();
        aTpsPass = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item,time);
        sp_aTpsPass.setAdapter(aTpsPass);
    }
    private  void getTimeArr(){
        setListTime();
        aTpsArr = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item,time);
        sp_tpsArr.setAdapter(aTpsArr);
    }


    private void getMach() {
        DaoIntervention.getInstance(this).getMach(result -> {
            if(result.equals(1)) {
                aMach = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getMachines());
                sp_mach.setAdapter(aMach);
            }else{
                Toast.makeText(CreateIntervention.this, "Erreur de chargement des machines !", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getAct() {
        DaoIntervention.getInstance(this).getActi(result -> {
            if(result.equals(1)){
                aAct = new ArrayAdapter<>(CreateIntervention.this, android.R.layout.simple_spinner_dropdown_item, DaoIntervention.getInstance(CreateIntervention.this).getActivites());
                sp_act.setAdapter(aAct);
            }else{
                Toast.makeText(CreateIntervention.this, "Erreur de chargement des Activité !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}