package com.magistrados.padaria.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VendasCliente {
    private Integer codigo;
    private String cnpj;
    private String data;
    private Integer toneladas;
    private Float valor;

    public VendasCliente() {
    }

    public VendasCliente(Integer codigo, String cnpj, String data, Integer toneladas, Float valor) {
        this.codigo = codigo;
        this.cnpj = cnpj;
        this.data = data;
        this.toneladas = toneladas;
        this.valor = valor;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getToneladas() {
        return toneladas;
    }

    public void setToneladas(Integer toneladas) {
        this.toneladas = toneladas;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
