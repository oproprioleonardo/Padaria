package com.magistrados.padaria;

import com.magistrados.padaria.api.database.ConnectionProvider;
import com.magistrados.padaria.api.repositories.ClienteRepository;
import com.magistrados.padaria.api.repositories.VendasClienteRepository;
import com.magistrados.padaria.graph.Screen;
import com.magistrados.padaria.internal.database.MicrosoftAccessConnectionProvider;
import com.magistrados.padaria.internal.repositories.AccessClienteRepository;
import com.magistrados.padaria.internal.repositories.AccessVendasClienteRepository;
import com.magistrados.padaria.services.VendasService;

public class Main {
    public static void main(String[] args) {
        final ConnectionProvider connectionProvider = new MicrosoftAccessConnectionProvider();
        final ClienteRepository clienteRepository = new AccessClienteRepository(connectionProvider);
        final VendasClienteRepository vendasClienteRepository = new AccessVendasClienteRepository(connectionProvider);
        final VendasService service = new VendasService(clienteRepository, vendasClienteRepository);
        final Screen screen = new Screen(service);
        screen.setVisible(true);
    }
}