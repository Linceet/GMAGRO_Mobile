package com.ageneste.gmagro.Beans;

public class Cd {
    private String codeCD;
    private String lib;
    private int codeEtab;

    public Cd(String codeCD, String lib, int codeEtab){
        this.codeCD = codeCD;
        this.lib = lib;
        this.codeEtab = codeEtab;
    }



    @Override
    public String toString() {
        return lib;
    }
}
