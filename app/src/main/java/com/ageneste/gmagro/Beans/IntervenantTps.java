package com.ageneste.gmagro.Beans;

import androidx.annotation.NonNull;

public class IntervenantTps {
    private Intervenant intervenant;
    private String tps;


    public IntervenantTps(Intervenant intervenant, String tps){
        this.intervenant = intervenant;
        this.tps = tps;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public String getTps() {
        return tps;
    }

    @Override
    public String toString() {
        return intervenant + " ("+ tps+")";
    }
}
