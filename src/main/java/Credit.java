package prevision;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connexion.MyConnection;

public class Credit {
    
    private int idcredit;
    private String libelle;
    private float montant;
    private Date datedebut;
    private Date datefin;

    public Credit(String libelle, float montant, Date datedebut, Date datefin) {
        this.libelle = libelle;
        this.montant = montant;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Credit() {}

    public int getIdcredit() {
        return idcredit;
    }
    public void setIdcredit(int idcredit) {
        this.idcredit = idcredit;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public float getMontant() {
        return montant;
    }
    public void setMontant(float montant) {
        this.montant = montant;
    }
    public Date getDatedebut() {
        return datedebut;
    }
    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }
    public Date getDatefin() {
        return datefin;
    }
    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public void save() throws Exception {
        String query = "INSERT INTO webdyncredit_credit(libelle, montant, datedebut, datefin) VALUES (?,?,?,?)";
        MyConnection mycon = new MyConnection();
        
        try (Connection connection = mycon.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setString(1, this.getLibelle());
            ps.setFloat(2, this.getMontant());
            ps.setDate(3, this.getDatedebut());
            ps.setDate(4, this.getDatefin());
            ps.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'insertion de la ligne de credit " + e.getMessage());
        }
    } 

    public static List<Credit> findAll() throws Exception {
        List<Credit> list = new ArrayList<>();
        String query = "SELECT * FROM webdyncredit_credit";
        MyConnection mycon = new MyConnection();

        try (Connection connection = mycon.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet result = ps.executeQuery()) {

                while(result.next()) {
                    int idcredit = result.getInt("idcredit");
                    String libelle = result.getString("libelle");
                    float montant = result.getFloat("montant");
                    Date datedebut = result.getDate("datedebut");
                    Date datefin = result.getDate("datefin");

                    Credit temp = new Credit();
                    temp.setIdcredit(idcredit);
                    temp.setLibelle(libelle);
                    temp.setMontant(montant);
                    temp.setDatedebut(datedebut);
                    temp.setDatefin(datefin);

                    list.add(temp);
                }
        } catch(Exception e) {
            throw new Exception("Erreur lors de la prise de toutes les lignes de credits " + e.getMessage());
        } 

        return list;
    }

    public static Credit findById(int idcredit) throws Exception {
        String query = "SELECT * FROM webdyncredit_credit WHERE idcredit = ?";
        MyConnection mycon = new MyConnection();
        Credit credit = new Credit();

        try (Connection connection = mycon.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idcredit);
            ResultSet result = ps.executeQuery();

                if(result.next()) {
                    String libelle = result.getString("libelle");
                    float montant = result.getFloat("montant");
                    Date datedebut = result.getDate("datedebut");
                    Date datefin = result.getDate("datefin");

                    credit.setIdcredit(idcredit);
                    credit.setLibelle(libelle);
                    credit.setMontant(montant);
                    credit.setDatedebut(datedebut);
                    credit.setDatefin(datefin);
                }
        } catch(Exception e) {
            throw new Exception("Erreur lors de la prise du credit " + e.getMessage());
        } 

        return credit;
    }

    public float getMontantDepense(int idutilisateur) throws Exception {
        float montantDepense = 0;
        String query = "SELECT SUM(montantdepense) as s FROM webdyncredit_depense WHERE idutilisateur = ? AND idcredit = ?";
        MyConnection mycon = new MyConnection();

        try (Connection connection = mycon.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idutilisateur);
            ps.setInt(2, this.getIdcredit());
            
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                montantDepense = result.getFloat("s");
            }

        } catch (Exception e) {
            throw new Exception("Montant depense non recupere" + e.getMessage());
        }

        return montantDepense;
    }
    
    public float getReste(int idutilisateur) throws Exception {
        float totalDepense = this.getMontantDepense(idutilisateur);
        float montantCredit = this.getMontant();
        float reste = montantCredit - totalDepense;
        return reste;
    }

    public boolean checkMontantSuffisant(float montantdepense, int idutilisateur) throws Exception {
        boolean result = true;
        float reste = this.getReste(idutilisateur);
        if(reste < montantdepense) { 
            result = false;
        }
        return result;
    }

    
}
