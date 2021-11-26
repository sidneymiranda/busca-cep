package com.sidney.buscacep.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "address_table")
public class Address implements Serializable {

    @PrimaryKey
    private Long UID;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public Address() {
    }

    public Address(String logradouro, String bairro, String localidade, String uf) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public Long getUID() { return UID; }

    public String getLogradouro() {
        return logradouro;
    }

    public void setUID(Long UID) { this.UID = UID; }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @NonNull
    @Override
    public String toString() {
        return "Address{" +
                "logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }

    //        "cep": "41110-300",
//            "logradouro": "Rua Flor de Maio",
//            "complemento": "",
//            "bairro": "Pernambués",
//            "localidade": "Salvador",
//            "uf": "BA",
//            "ibge": "2927408",
//            "gia": "",
//            "ddd": "71",
//            "siafi": "3849"
}
