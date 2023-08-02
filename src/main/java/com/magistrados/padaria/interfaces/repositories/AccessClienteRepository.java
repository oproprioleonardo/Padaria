package com.magistrados.padaria.interfaces.repositories;

import com.magistrados.padaria.api.database.ConnectionProvider;
import com.magistrados.padaria.api.repositories.ClienteRepository;
import com.magistrados.padaria.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessClienteRepository implements ClienteRepository {

    private final ConnectionProvider connectionProvider;

    public AccessClienteRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Cliente consultar(String id) {
        try (final Connection connection = this.connectionProvider.getConnection()) {
            final Cliente cliente = new Cliente();
            final PreparedStatement st = connection.prepareStatement("SELECT * FROM TabClientes where cnpj = ?");
            st.setString(1, id);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cliente.setCnpj(rs.getString("cnpj"));
                cliente.setNome(rs.getString("nome"));
                return cliente;
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
