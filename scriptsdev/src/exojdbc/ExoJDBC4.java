/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exojdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExoJDBC4 {
    public static void main(String[] args) {
        // Établir la connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/devdata";
        String user = "root";
        String password = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // Établir la connexion
            conn = DriverManager.getConnection(url, user, password);

            // Créer un objet Statement
            stmt = conn.createStatement();

            // Partie 1 : Trouver la personne ayant réalisé le nombre maximum de scripts en une journée
            String queryMaxScripts = "SELECT Developpeurs, jour, max(NBScripts) as max_scripts " +
                                     "FROM devdata " +
                                     "GROUP BY jour";
            rs = stmt.executeQuery(queryMaxScripts);

            // Parcourir les résultats
            System.out.println("Personne ayant réalisé le nombre maximum de scripts en une journée :");
            while (rs.next()) {
                String developpeur = rs.getString("Developpeurs");
                String jour = rs.getString("jour");
                int maxScripts = rs.getInt("max_scripts");

                System.out.println("Le développeur " + developpeur + " a réalisé " + maxScripts + " scripts le " + jour);
            }

            // Fermer l'objet ResultSet pour libérer les ressources
            rs.close();

            // Partie 2 : Calculer et afficher le nombre total de scripts réalisés en une semaine
            String queryTotalScripts = "SELECT SUM(NBScripts) AS total_scripts_semaine " +
                                       "FROM devdata";
            rs = stmt.executeQuery(queryTotalScripts);

            // Parcourir les résultats
            if (rs.next()) {
                int totalScripts = rs.getInt("total_scripts_semaine");
                System.out.println("Nombre total de scripts réalisés en une semaine : " + totalScripts);
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver JDBC");
        } finally {
            // Fermer les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Erreur de fermeture des ressources");
            }
        }
    }
}

