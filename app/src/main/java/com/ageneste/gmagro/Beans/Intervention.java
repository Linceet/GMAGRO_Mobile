package com.ageneste.gmagro.Beans;

public class Intervention {
    private int idIntervention;
    private String dh_debut;
    private String dh_fin;
    private String commentaire;
    private String temps_arret;
    private boolean changement_organe;
    private boolean perte;
    private String dh_creation;
    private String dh_derniere_maj;
    private String idInterv;
    private String codeAct;
    private String codeMachine;
    private String codeCD;
    private String codeCO;
    private String codeSD;
    private String codeSO;

    public Intervention(int idIntervention, String dh_debut, String dh_fin, String commentaire, String temps_arret, boolean changement_organe, boolean perte, String dh_creation, String dh_derniere_maj, String idInterv, String codeAct, String codeMachine, String codeCD, String codeCO, String codeSD, String codeSO) {
        this.idIntervention = idIntervention;
        this.dh_debut = dh_debut;
        this.dh_fin = dh_fin;
        this.commentaire = commentaire;
        this.temps_arret = temps_arret;
        this.changement_organe = changement_organe;
        this.perte = perte;
        this.dh_creation = dh_creation;
        this.dh_derniere_maj = dh_derniere_maj;
        this.idInterv = idInterv;
        this.codeAct = codeAct;
        this.codeMachine = codeMachine;
        this.codeCD = codeCD;
        this.codeCO = codeCO;
        this.codeSD = codeSD;
        this.codeSO = codeSO;
    }

    @Override
    public String toString() {
        return  idIntervention+" "+ idInterv ;

    }
}
