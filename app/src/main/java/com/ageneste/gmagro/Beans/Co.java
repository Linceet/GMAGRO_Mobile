package com.ageneste.gmagro.Beans;

public class Co {
    private String codeCo;
    private String lib;
    private int codeEtab;

    public Co(String codeCo, String lib, int codeEtab){
        this.codeCo = codeCo;
        this.lib = lib;
        this.codeEtab = codeEtab;
    }



    @Override
    public String toString() {
        return lib;
    }
}
