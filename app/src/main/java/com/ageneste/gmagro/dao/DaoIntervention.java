package com.ageneste.gmagro.dao;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ageneste.gmagro.Beans.Activite;
import com.ageneste.gmagro.ws.WSConnexionHTTPS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class DaoIntervention {
    private static DaoIntervention instance = null;
    private final List<Activite> activites;

    private DaoIntervention(List<Activite> activites) {
        this.activites = activites;
    }

    public static DaoIntervention getInstance(Context c) {
        if (instance == null) {
            instance = new DaoIntervention();
        }
        return instance;
    }

    public void getActi(DelegateAsyncTask dlg) {
        String url = "uc=logUser&id=";
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterGetAct(s, dlg);
            }
        };
        ws.execute(url);
    }


    private void traiterGetAct(String s, DelegateAsyncTask dlg) throws JSONException {
        JSONArray  jar = new JSONArray(s);
        for(int i=0; i<jar.length() ;i++) {
            JSONObject jo = jar.getJSONObject(i);
            String code = jo.get("codeAct").toString();
            String lib= jo.get("libelle").toString();
            Activite act = new Activite(code, lib);
        }


        dlg.whenWSConnexionIsTerminated();
    }

}
