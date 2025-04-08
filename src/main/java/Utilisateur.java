package login;
import login.BaseModel;

public class Utilisateur extends BaseModel {
    
    private String nom;
    private String mdp;

    public Utilisateur(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }

    public Utilisateur(int id, String nom, String mdp) {
        super.setId(id);
        this.nom = nom;
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    } public void setNom(String nom) {
        this.nom = nom;
    } public String getMdp() {
        return mdp;
    } public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
