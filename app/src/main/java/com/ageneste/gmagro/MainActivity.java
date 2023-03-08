package com.ageneste.gmagro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ageneste.gmagro.dao.DaoIntervenant;
import com.ageneste.gmagro.dao.DelegateAsyncTask;

public class MainActivity extends AppCompatActivity {
    private EditText et_log;
    private EditText et_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         et_log = findViewById(R.id.et_login);
         et_pass = findViewById(R.id.et_pass);

         findViewById(R.id.btn_connexion).setOnClickListener(this::clicBtnConnexion);

    }

    private void clicBtnConnexion(View v){
        String log = et_log.getText().toString();
        String pass = et_pass.getText().toString();
        if(!log.isEmpty() && !pass.isEmpty()){
            DaoIntervenant.getInstance(this).Login(log, pass, new DelegateAsyncTask() {
                @Override
                public void whenWSConnexionIsTerminated(Object s) {
                    if(s!=null) {
                        Intent inting = new Intent(MainActivity.this, menuIntervention.class);
                        startActivityForResult(inting,0);
                        Toast.makeText(MainActivity.this, "connection reussi!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "verifier votre mots de pass ou votre identifiant!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0){
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        } else if (resultCode == 1) {
            Toast.makeText(this, "Déconnecter!", Toast.LENGTH_SHORT).show();
        }

    }
}