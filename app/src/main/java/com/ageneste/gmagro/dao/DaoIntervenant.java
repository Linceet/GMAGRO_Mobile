package com.ageneste.gmagro.dao;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ageneste.gmagro.Beans.Intervenant;
import com.ageneste.gmagro.Beans.IntervenantTps;
import com.ageneste.gmagro.Beans.So;
import com.ageneste.gmagro.ws.WSConnexionHTTPS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoIntervenant {
    private static DaoIntervenant instance = null;
    private Intervenant moi = null;
    private final List<Intervenant> intervs;

    private DaoIntervenant() {
        intervs = new ArrayList<>();
    }

    public List<Intervenant> getIntervs(){return intervs;}


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

    public void getAllPersonne(DelegateAsyncTask dlg){
        String url = "uc=getAllIntervs";
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                try {
                    traiterAllPersonne(s, dlg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ws.execute(url);
    }

    private void traiterAllPersonne(String s, DelegateAsyncTask dlg) throws JSONException {
        intervs.clear();
        JSONArray jar = new JSONArray(s);
        for(int i=0; i<jar.length() ;i++) {
            JSONObject jor = jar.getJSONObject(i);
            String mail = jor.get("mail").toString();
            String nom = jor.get("nom").toString();
            String prenom = jor.get("prenom").toString();
            Boolean isAct = jor.getInt("actif") == 1;
            int codeR = jor.getInt("codeRole");
            int codeE = jor.getInt("codeEtab");

            Intervenant inter = new Intervenant(mail, prenom, nom, isAct, codeR, codeE);
            intervs.add(inter);
        }
        if (intervs.size() == 0) {
            dlg.whenWSConnexionIsTerminated(null);
        }else{
            dlg.whenWSConnexionIsTerminated(1);
        }
    }

    public void removeIntervFromList(Intervenant interv){
        intervs.remove(interv);
    }
    public void addIntervFromLV(IntervenantTps intervTps){
        Intervenant interv = intervTps.getIntervenant();
        intervs.add(interv);

    }

}
