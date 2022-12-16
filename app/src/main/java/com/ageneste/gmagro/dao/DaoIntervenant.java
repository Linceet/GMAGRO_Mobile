package com.ageneste.gmagro.dao;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ageneste.gmagro.Beans.Intervenant;
import com.ageneste.gmagro.ws.WSConnexionHTTPS;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DaoIntervenant {
    private static DaoIntervenant instance = null;
    private Intervenant moi = null;

    private DaoIntervenant() {

    }
    public static DaoIntervenant getInstance(Context c) {
        if (instance == null) {
            instance = new DaoIntervenant();
        }
        return instance;
    }

    public Intervenant getMoi() {
        return moi;
    }

    public void Login(String id, String mdp, DelegateAsyncTask dlg){
        String url = "uc=logUser&id=" + id + "&pass=" + mdp;
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterConnect(s, dlg);
            }
        };
        ws.execute(url);
    }
    private void traiterConnect(String s, DelegateAsyncTask dlg) {
        if (!s.equals("2")) {
            try {
                JSONObject jar = new JSONObject(s);
                String mail = jar.get("mail").toString();
                String nom = jar.get("nom").toString();
                String prenom = jar.get("prenom").toString();
                Boolean isAct = jar.getInt("actif") == 1;
                int codeR = jar.getInt("codeRole");
                int codeE = jar.getInt("codeEtab");

                moi = new Intervenant(mail, prenom, nom, isAct, codeR, codeE);
                dlg.whenWSConnexionIsTerminated(moi);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            dlg.whenWSConnexionIsTerminated(null);
        }
    }
    public void deco(DelegateAsyncTask dlg){
        String url = "uc=deco";
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterDeco(s, dlg);
            }
        };
        ws.execute(url);
    }

    private void traiterDeco(String s, DelegateAsyncTask dlg) {
        if(s!=null) {
            dlg.whenWSConnexionIsTerminated(1);
        }
    }
}
