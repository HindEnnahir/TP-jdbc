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

    public class ExoJDBC {
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

            // Exécuter la requête SQL
            String query = "SELECT Developpeurs, jour, max(NBScripts) as max_scripts " +
                           "FROM devdata " +
                           "GROUP BY jour";
            rs = stmt.executeQuery(query);

            // Parcourir les résultats
            while (rs.next()) {
                String developpeur = rs.getString("Developpeurs");
                String jour = rs.getString("jour");
                int maxScripts = rs.getInt("max_scripts");

                System.out.println("Le développeur " + developpeur + " a réalisé " + maxScripts + " scripts le " + jour);
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