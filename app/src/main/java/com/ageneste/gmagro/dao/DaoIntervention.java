package com.ageneste.gmagro.dao;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ageneste.gmagro.Beans.Activite;
import com.ageneste.gmagro.Beans.Cd;
import com.ageneste.gmagro.Beans.Co;
import com.ageneste.gmagro.Beans.Machine;
import com.ageneste.gmagro.ws.WSConnexionHTTPS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DaoIntervention {
    private static DaoIntervention instance = null;
    private final List<Activite> activites;
    private final List<Machine> machines;
    private final List<Cd> cds;
    private final List<Co> cos;

    private DaoIntervention() {
        activites = new ArrayList<>();
        machines = new ArrayList<>();
        cds = new ArrayList<>();
        cos = new ArrayList<>();
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public List<Cd> getCds() {
        return cds;
    }

    public static DaoIntervention getInstance(Context c) {
        if (instance == null) {
            instance = new DaoIntervention();
        }
        return instance;
    }

    public void getActi(DelegateAsyncTask dlg) {
        String url = "uc=getActivite";
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                try {
                    traiterGetAct(s, dlg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ws.execute(url);
    }


    private void traiterGetAct(String s, DelegateAsyncTask dlg) throws JSONException {
        activites.clear();
        JSONArray  jar = new JSONArray(s);
        for(int i=0; i<jar.length() ;i++) {
            JSONObject jo = jar.getJSONObject(i);
            String code = jo.get("codeAct").toString();
            String lib= jo.get("libelle").toString();
            Activite act = new Activite(code, lib);
            activites.add(act);
        }
        if (activites.size() == 0) {
            dlg.whenWSConnexionIsTerminated(null);
        }else{
            dlg.whenWSConnexionIsTerminated(1);
        }
    }

    public void getMach(int codeE, DelegateAsyncTask dlg) {
        String url = "uc=getMachine&codeE="+codeE;
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                try {
                    traiterGetMach(s, dlg);
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        ws.execute(url);
    }


    private void traiterGetMach(String s, DelegateAsyncTask dlg) throws JSONException, ParseException {
        machines.clear();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray  jar = new JSONArray(s);
        for(int i=0; i<jar.length() ;i++) {
            JSONObject jo = jar.getJSONObject(i);
            String codeMachine = jo.get("codeMachine").toString();
            String lib = jo.get("libelle").toString();
            Date dateCreat = sdf.parse(jo.getString("date_fabric"));
            int num_ser = jo.getInt("numero_serie");
            int codeE = jo.getInt("codeEtab");
            int codeType = jo.getInt("codeType");
            Machine m = new Machine(codeMachine, lib, dateCreat,num_ser, codeE, codeType);
            machines.add(m);
        }
        if (machines.size() == 0) {
            dlg.whenWSConnexionIsTerminated(null);
        }else{
            dlg.whenWSConnexionIsTerminated(1);
        }
    }

    public void getCd(int codeE,DelegateAsyncTask dlg) {
        String url = "uc=getCD&codeE="+codeE;
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                try {
                    traiterGetCd(s, dlg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ws.execute(url);
    }


    private void traiterGetCd(String s, DelegateAsyncTask dlg) throws JSONException {
        cds.clear();
        JSONArray  jar = new JSONArray(s);
        for(int i=0; i<jar.length() ;i++) {
            JSONObject jo = jar.getJSONObject(i);
            String codeCD = jo.get("codeCD").toString();
            String lib= jo.get("libelle").toString();
            Integer codeEtab = jo.optInt("codeEtab", 0);
            Cd cd = new Cd(codeCD,lib,codeEtab);
            cds.add(cd);
        }
        if (cds.size() == 0) {
            dlg.whenWSConnexionIsTerminated(null);
        }else{
            dlg.whenWSConnexionIsTerminated(1);
        }
    }

    public void getCo(int codeE,DelegateAsyncTask dlg) {
        String url = "uc=getCO&codeE="+codeE;
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                try {
                    traiterGetCo(s, dlg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ws.execute(url);
    }


    private void traiterGetCo(String s, DelegateAsyncTask dlg) throws JSONException {
        cos.clear();
        JSONArray  jar = new JSONArray(s);
        for(int i=0; i<jar.length() ;i++) {
            JSONObject jo = jar.getJSONObject(i);
            String codeCO = jo.get("codeCO").toString();
            String lib= jo.get("libelle").toString();
            Integer codeEtab = jo.optInt("codeEtab", 0);
            Co co = new Co(codeCO,lib,codeEtab);
            cos.add(co);
        }
        if (cos.size() == 0) {
            dlg.whenWSConnexionIsTerminated(null);
        }else{
            dlg.whenWSConnexionIsTerminated(1);
        }
    }

}
