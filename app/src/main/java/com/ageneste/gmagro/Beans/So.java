package com.ageneste.gmagro.Beans;

public class So {
    private String codeSo;
    private String lib;
    private int codeEtab;

    public So(String codeSo, String lib, int codeEtab){
        this.codeSo = codeSo;
        this.lib = lib;
        this.codeEtab = codeEtab;
    }

    public String getCodeSo() {
        return codeSo;
    }

    @Override
    public String toString() {
        return lib;
    }
}
