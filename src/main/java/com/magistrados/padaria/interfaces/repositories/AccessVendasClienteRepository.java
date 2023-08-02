package com.magistrados.padaria.interfaces.repositories;

import com.magistrados.padaria.api.database.ConnectionProvider;
import com.magistrados.padaria.api.repositories.VendasClienteRepository;
import com.magistrados.padaria.models.Cliente;
import com.magistrados.padaria.models.VendasCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessVendasClienteRepository implements VendasClienteRepository {

    private final ConnectionProvider connectionProvider;

    public AccessVendasClienteRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public List<VendasCliente> procurarVendas(Cliente cliente) {
        final List<VendasCliente> vendasClientes = new ArrayList<>();
        try (final Connection connection = this.connectionProvider.getConnection()) {
            final PreparedStatement st = connection.prepareStatement("SELECT * FROM TabVendasCliente where cnpj = ?");
            st.setString(1, cliente.getCnpj());
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final VendasCliente vendasCliente = new VendasCliente();
                vendasCliente.setCodigo(rs.getInt("codigo"));
                vendasCliente.setCnpj(cliente.getCnpj());
                vendasCliente.setData(rs.getString("data"));
                vendasCliente.setToneladas(Integer.valueOf(rs.getString("toneladas")));
                vendasCliente.setValor(Float.valueOf(rs.getString("valor").replace(",", ".")));
                vendasClientes.add(vendasCliente);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendasClientes;
    }

    @Override
    public VendasCliente consultar(Integer id) {
        try (final Connection connection = this.connectionProvider.getConnection()) {
            final VendasCliente vendasCliente = new VendasCliente();
            final PreparedStatement st = connection.prepareStatement("SELECT * FROM TabVendasCliente where codigo = ?");
            st.setInt(1, id);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                vendasCliente.setCodigo(rs.getInt("codigo"));
                vendasCliente.setCnpj(rs.getString("cnpj"));
                vendasCliente.setData(rs.getString("data"));
                vendasCliente.setToneladas(Integer.valueOf(rs.getString("toneladas")));
                vendasCliente.setValor(Float.valueOf(rs.getString("valor").replace(",", ".")));
            }
            st.close();
            return vendasCliente;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
