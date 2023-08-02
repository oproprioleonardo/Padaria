package com.magistrados.padaria.services;

import com.magistrados.padaria.api.repositories.ClienteRepository;
import com.magistrados.padaria.api.repositories.VendasClienteRepository;
import com.magistrados.padaria.models.Cliente;
import com.magistrados.padaria.models.VendasCliente;

import java.util.List;

public class VendasService {

    private final ClienteRepository clienteRepository;
    private final VendasClienteRepository vendasClienteRepository;

    public VendasService(ClienteRepository clienteRepository, VendasClienteRepository vendasClienteRepository) {
        this.clienteRepository = clienteRepository;
        this.vendasClienteRepository = vendasClienteRepository;
    }

    public Cliente getCliente(String cnpj) {
        return this.clienteRepository.consultar(cnpj);
    }

    public List<VendasCliente> getVendasCliente(String cnpj) {
        final Cliente cliente = this.clienteRepository.consultar(cnpj);
        return this.vendasClienteRepository.procurarVendas(cliente);
    }

    public List<VendasCliente> getVendasCliente(Cliente cliente) {
        return this.vendasClienteRepository.procurarVendas(cliente);
    }


}
