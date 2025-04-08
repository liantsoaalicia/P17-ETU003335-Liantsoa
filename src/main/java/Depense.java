package prevision;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connexion.MyConnection;

public class Depense {
    
    private int iddepense;
    private int idutilisateur;
    private int idcredit;
    private float montantdepense;
    private Date datedepense;
    
    public Depense(int idutilisateur, int idcredit, float montantdepense, Date datedepense) {
        this.idutilisateur = idutilisateur;
        this.idcredit = idcredit;
        this.montantdepense = montantdepense;
        this.datedepense = datedepense;
    }

    public Depense() {}
    
    public int getIdutilisateur() {
        return idutilisateur;
    }
    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }
    public int getIddepense() {
        return iddepense;
    }
    public void setIddepense(int iddepense) {
        this.iddepense = iddepense;
    }
    public int getIdcredit() {
        return idcredit;
    }
    public void setIdcredit(int idcredit) {
        this.idcredit = idcredit;
    }
    public float getMontantdepense() {
        return montantdepense;
    }
    public void setMontantdepense(float montantdepense) {
        this.montantdepense = montantdepense;
    }
    public Date getDatedepense() {
        return datedepense;
    }
    public void setDatedepense(Date datedepense) {
        this.datedepense = datedepense;
    }


    public void save() throws Exception {
        String query = "INSERT INTO webdyncredit_depense(idutilisateur, idcredit, montantdepense, datedepense) VALUES (?,?,?,?)";
        MyConnection mycon = new MyConnection();
        
        try (Connection connection = mycon.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setInt(1, this.getIdutilisateur());
            ps.setInt(2, this.getIdcredit());
            ps.setFloat(3, this.getMontantdepense());
            ps.setDate(4, this.getDatedepense());
            ps.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'insertion de la ligne de depense " + e.getMessage());
        }
    } 

    public static List<Depense> findAll() throws Exception {
        List<Depense> liste = new ArrayList<>();
        String query = "SELECT * FROM webdyncredit_depense";
        MyConnection mycon = new MyConnection();

        try (Connection connection = mycon.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet result = ps.executeQuery()) {

                while(result.next()) {
                    int idcredit = result.getInt("idcredit");
                    float montantdepense = result.getFloat("montantdepense");
                    Date datedepense = result.getDate("datedepense");
                    
                    Depense temp = new Depense();
                    temp.setIdcredit(idcredit);
                    temp.setMontantdepense(montantdepense);
                    temp.setDatedepense(datedepense);

                    liste.add(temp);
                }
        } catch(Exception e) {
            throw new Exception("Erreur lors de la prise de toutes les lignes de credits " + e.getMessage());
        } 

        return liste;
    }

}
