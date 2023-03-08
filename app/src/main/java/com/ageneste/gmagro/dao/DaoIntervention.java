package com.ageneste.gmagro.dao;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ageneste.gmagro.Beans.Activite;
import com.ageneste.gmagro.Beans.Cd;
import com.ageneste.gmagro.Beans.Co;
import com.ageneste.gmagro.Beans.Intervenant;
import com.ageneste.gmagro.Beans.Intervention;
import com.ageneste.gmagro.Beans.Machine;
import com.ageneste.gmagro.Beans.Sd;
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


public class DaoIntervention {
    private static DaoIntervention instance = null;
    private final List<Activite> activites;
    private final List<Machine> machines;
    private final List<Cd> cds;
    private final List<Co> cos;
    private final List<Sd> sds;
    private final List<So> sos;
    private final List<Intervention> intervs;

    private DaoIntervention() {
        activites = new ArrayList<>();
        machines = new ArrayList<>();
        cds = new ArrayList<>();
        cos = new ArrayList<>();
        sds = new ArrayList<>();
        sos = new ArrayList<>();
        intervs = new ArrayList<>();
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

    public List<Co> getCos() {
        return cos;
    }

    public List<Sd> getSds(){return sds;}

    public List<So> getSos(){return sos;}

    public List<Intervention> getIntervs() {
        return intervs;
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

    public void getMach(DelegateAsyncTask dlg) {
        String url = "uc=getMachine";
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

    public void getCd(DelegateAsyncTask dlg) {
        String url = "uc=getCD";
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

    public void getCo(DelegateAsyncTask dlg) {
        String url = "uc=getCO";
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

    public void getSd(DelegateAsyncTask dlg) {
        String url = "uc=getSD";
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                try {
                    traiterGetSd(s, dlg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ws.execute(url);
    }


    private void traiterGetSd(String s, DelegateAsyncTask dlg) throws JSONException {
        sds.clear();
        JSONArray  jar = new JSONArray(s);
        for(int i=0; i<jar.length() ;i++) {
            JSONObject jo = jar.getJSONObject(i);
            String codeSD = jo.get("codeSD").toString();
            String lib= jo.get("libelle").toString();
            int codeEtab = jo.optInt("codeEtab", 0);
            Sd sd = new Sd(codeSD,lib,codeEtab);
            sds.add(sd);
        }
        if (sds.size() == 0) {
            dlg.whenWSConnexionIsTerminated(null);
        }else{
            dlg.whenWSConnexionIsTerminated(1);
        }
    }

    public void getSo(DelegateAsyncTask dlg) {
        String url = "uc=getSO";
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                try {
                    traiterGetSo(s, dlg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ws.execute(url);
    }


    private void traiterGetSo(String s, DelegateAsyncTask dlg) throws JSONException {
        sos.clear();
        JSONArray  jar = new JSONArray(s);
        for(int i=0; i<jar.length() ;i++) {
            JSONObject jo = jar.getJSONObject(i);
            String codeSO = jo.get("codeSO").toString();
            String lib= jo.get("libelle").toString();
            int codeEtab = jo.optInt("codeEtab", 0);
            So so = new So(codeSO,lib,codeEtab);
            sos.add(so);
        }
        if (sos.size() == 0) {
            dlg.whenWSConnexionIsTerminated(null);
        }else{
            dlg.whenWSConnexionIsTerminated(1);
        }
    }


    public void addIntervention(String cAct,String cMach,String cCd,String cCo,String cSd,String cSo,String cCom,int cChO,int cPer,String cTpsPass,String cTpsArr,String cMsg,DelegateAsyncTask dlg) {
        String url = "uc=addIntervention&cAct="+cAct+"&cMach="+cMach+"&cCd="+cCd+"&cCo="+cCo+"&cSd="+cSd+"&cSo="+cSo+"&cCom="+cCom+"&cCgO="+cChO+"&cPer="+cPer+"&cTpsPass="+cTpsPass+"&cTpsArr="+cTpsArr+"&cInt="+cMsg;
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                traiterAddIntervention(s, dlg);
            }
        };
        ws.execute(url);
    }
    private void traiterAddIntervention(String s, DelegateAsyncTask dlg) {
        if (s.equals("1")) {
            dlg.whenWSConnexionIsTerminated(1);
        }else{
            dlg.whenWSConnexionIsTerminated(0);
        }
    }

    public void getIntervention(DelegateAsyncTask dlg) {
        String url = "uc=getIntervention";
        @SuppressLint("StaticFieldLeak") WSConnexionHTTPS ws = new WSConnexionHTTPS() {
            @Override
            protected void onPostExecute(String s) {
                try {
                    traiterGetIntervention(s, dlg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ws.execute(url);
    }


    private void traiterGetIntervention(String s, DelegateAsyncTask dlg) throws JSONException {
        intervs.clear();
        JSONArray  jar = new JSONArray(s);
        for(int i=0; i<jar.length() ;i++) {
            JSONObject jo = jar.getJSONObject(i);
            int idInterv = jo.getInt("idIntervention");
            String dh_deb = jo.get("dh_debut").toString();
            String dh_fin= jo.get("dh_fin").toString();
            String com = jo.get("commentaire").toString();
            String tps_arr = jo.get("temps_arret").toString();
            boolean chO = jo.getInt("changement_organe")==1;
            boolean pert = jo.getInt("perte")==1;
            String dh_create = jo.get("dh_creation").toString();
            String dh_maj = jo.get("dh_derniere_maj").toString();
            String idInter = jo.get("idInterv").toString();
            String codeAct = jo.get("codeAct").toString();
            String codeMach = jo.get("codeMachine").toString();
            String codeCd = jo.get("codeCD").toString();
            String codeCo = jo.get("codeCO").toString();
            String codeSd = jo.get("codeSD").toString();
            String codeSo = jo.get("codeSO").toString();
            Intervention inter = new Intervention(idInterv,dh_deb,dh_fin,com,tps_arr,chO,pert,dh_create,dh_maj,idInter,codeAct,codeMach,codeCd,codeCo,codeSd,codeSo);
            intervs.add(inter);
        }
        if (intervs.size() == 0) {
            dlg.whenWSConnexionIsTerminated(null);
        }else{
            dlg.whenWSConnexionIsTerminated(1);
        }
    }


}
