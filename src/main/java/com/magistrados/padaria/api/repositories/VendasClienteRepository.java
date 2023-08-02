package com.magistrados.padaria.api.repositories;

import com.magistrados.padaria.api.repositories.base.BaseRepository;
import com.magistrados.padaria.models.Cliente;
import com.magistrados.padaria.models.VendasCliente;

import java.util.List;

public interface VendasClienteRepository extends BaseRepository<VendasCliente, Integer> {
    List<VendasCliente> procurarVendas(Cliente cliente);
}
