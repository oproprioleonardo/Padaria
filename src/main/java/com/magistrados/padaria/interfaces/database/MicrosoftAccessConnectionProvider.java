package com.magistrados.padaria.interfaces.database;

import com.magistrados.padaria.api.database.ConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MicrosoftAccessConnectionProvider implements ConnectionProvider {

    public MicrosoftAccessConnectionProvider() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:ucanaccess://BDFarinha.mdb");
    }
}
