package com.ageneste.gmagro.Beans;

public class Activite {
    private String code;
    private String lib;

    public Activite(String code, String lib){
        this.code = code;
        this.lib=lib;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + " : " + lib;
    }
}
