package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Konekcija {
    private static Konekcija instance;
    private Connection connection;

    private Konekcija() {
        try {
            String url = "jdbc:mysql://localhost:3306/knjige";
            connection = DriverManager.getConnection(url, "root", "1234");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Konekcija getInstance() {
        if (instance == null) {
            instance = new Konekcija();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
