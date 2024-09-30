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
import java.util.Scanner;

public class ExoJDBC3 {

    public static void main(String[] args) {
        // Établir la connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/devdata";
        String user = "root";
        String password = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Demander le nom du programmeur
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du programmeur : ");
        String programmeur = scanner.nextLine();

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // Établir la connexion
            conn = DriverManager.getConnection(url, user, password);

            // Créer un objet Statement
            stmt = conn.createStatement();

            // Requête pour calculer le nombre total de scripts réalisés par le programmeur donné
            String queryTotalScriptsByDev = "SELECT Developpeurs, SUM(NBScripts) AS total_scripts "
                    + "FROM devdata "
                    + "WHERE Developpeurs = '" + programmeur + "' "
                    + "GROUP BY Developpeurs";

            rs = stmt.executeQuery(queryTotalScriptsByDev);

            // Parcourir les résultats
            if (rs.next()) {
                String dev = rs.getString("Developpeurs");
                int totalScripts = rs.getInt("total_scripts");
                System.out.println("Le développeur " + dev + " a réalisé un total de " + totalScripts + " scripts.");
            } else {
                System.out.println("Aucun résultat pour le programmeur " + programmeur);
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

        // Fermer le scanner
        scanner.close();
    }
}
