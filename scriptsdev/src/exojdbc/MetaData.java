/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MetaData {

    public static void main(String[] args) {
        // Établir la connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/devdata";
        String user = "root";
        String password = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Demander la requête SQL à l'utilisateur
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre requête SQL : ");
        String query = scanner.nextLine();

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            // Établir la connexion
            conn = DriverManager.getConnection(url, user, password);

            // Créer un objet Statement
            stmt = conn.createStatement();

            // Exécuter la requête, déterminer si elle retourne un ResultSet ou si elle modifie des lignes
            boolean isResultSet = stmt.execute(query);

            if (isResultSet) {
                // Si la requête produit un ResultSet
                rs = stmt.getResultSet();

                // Obtenir les métadonnées du ResultSet
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Afficher le nombre de colonnes
                System.out.println("Nombre de colonnes : " + columnCount);

                // Afficher le nom et le type des colonnes
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnType = metaData.getColumnTypeName(i);
                    System.out.println("Colonne " + i + ": " + columnName + " (Type: " + columnType + ")");
                }

                // Afficher le contenu du ResultSet
                System.out.println("Contenu de la table :");
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } else {
                // Si la requête ne produit pas un ResultSet mais modifie des lignes
                int rowsAffected = stmt.getUpdateCount();
                System.out.println("Nombre de lignes modifiées : " + rowsAffected);
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
