package com.ageneste.gmagro.Beans;

public class Sd {
    private String codeSd;
    private String lib;
    private int codeEtab;

    public Sd(String codeSd, String lib, int codeEtab){
        this.codeSd = codeSd;
        this.lib = lib;
        this.codeEtab = codeEtab;
    }

    public String getCodeSd() {
        return codeSd;
    }

    @Override
    public String toString() {
        return lib;
    }
}
