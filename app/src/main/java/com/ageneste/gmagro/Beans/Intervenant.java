package com.ageneste.gmagro.Beans;

public class Intervenant {
    private String mail;
    private String prenom;
    private String nom;
    private boolean isActif;
    private int codeRole;
    private int codeEtab;

    public Intervenant(String mail, String prenom, String nom, Boolean isActif, int codeRole, int codeEtab) {
        this.mail = mail;
        this.prenom = prenom;
        this.nom = nom;
        this.isActif = isActif;
        this.codeRole = codeRole;
        this.codeEtab = codeEtab;
    }

    @Override
    public String toString() {
        return nom + " " +prenom;

    }
}
