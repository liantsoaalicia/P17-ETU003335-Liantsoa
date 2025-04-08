package login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connexion.MyConnection;
import login.BaseModel;

public class UtilisateurDAO extends UtilisateurInterface {

    public static Utilisateur findById(int id) throws Exception {
        Utilisateur utilisateur = null; 
        MyConnection mycon = new MyConnection();
        String query = "SELECT * FROM webdyncredit_utilisateur WHERE idutilisateur = ?";
        Connection connection = null;

        try {
            connection = mycon.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if(result.next()) {
                utilisateur = new Utilisateur(
                    result.getInt("idutilisateur"),
                    result.getString("nom"),
                    result.getString("mdp")
                );
            }

            ps.close();
            result.close();
        } catch(Exception e) {
            throw new Exception("Utilisateur non trouve dans findById " +e.getMessage());
        }

        return utilisateur;
    }

    public static Utilisateur findByName(String nom, String mdp) throws Exception {
        Utilisateur utilisateur = null; 
        MyConnection mycon = new MyConnection();
        String query = "SELECT * FROM webdyncredit_utilisateur WHERE nom = ? AND mdp = ?";
        Connection connection = null;

        try {
            connection = mycon.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nom);
            ps.setString(2, mdp);
            ResultSet result = ps.executeQuery();

            if(result.next()) {
                utilisateur = new Utilisateur(
                    result.getInt("idutilisateur"),
                    result.getString("nom"),
                    result.getString("mdp")
                );
            }

            ps.close();
            result.close();
        } catch(Exception e) {
            throw new Exception("Utilisateur non trouve dans findByName " + e.getMessage());
        }

        return utilisateur;
    }
    
    public static boolean ifMatched(BaseModel b) throws Exception {
        if (!(b instanceof Utilisateur)) {
            throw new Exception ("L'argument doit être un Utilisateur");
        }

        MyConnection mycon = new MyConnection();
        Connection con = null;
        String query = "SELECT * FROM webdyncredit_utilisateur WHERE nom = ? AND mdp = ?";
        Utilisateur utilisateur = (Utilisateur) b;
        boolean match = false;

        try {
            con = mycon.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getMdp());
            ResultSet result = ps.executeQuery();

            if(result.next()) {
                match = true;
            }

            ps.close();
            result.close();
        } catch(Exception e) {
            throw new Exception("Utilisateur non trouve dans ifMatched" + e.getMessage());
        }

        return match;
    }

    

}
