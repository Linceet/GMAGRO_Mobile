package com.ageneste.gmagro.Beans;

import java.util.Date;

public class Machine {
    private String codeMachine;
    private String libelle;
    private Date datefab;
    private int numSer;
    private int codeEtab;
    private int codeType;


    public Machine(String codeMachine, String libelle, Date datefab, int numSer, int codeEtab, int codeType) {
        this.codeMachine = codeMachine;
        this.libelle = libelle;
        this.datefab = datefab;
        this.numSer = numSer;
        this.codeEtab = codeEtab;
        this.codeType = codeType;

    }

    @Override
    public String toString() {
        return libelle;
    }
}
