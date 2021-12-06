package com.sidney.buscacep.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "address_table")
public class Address implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long UID;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String cep;

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getLogradouro() {
        return logradouro;
    }

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getCep().equals(address.getCep());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCep());
    }

    public static class AddressBuilder {
        private Long UID;
        private String logradouro;
        private String bairro;
        private String localidade;
        private String uf;
        private String cep;

        private AddressBuilder() {
        }

        public static AddressBuilder builder() {
            return new AddressBuilder();
        }

        public AddressBuilder setUID(Long UID) {
            this.UID = UID;
            return this;
        }

        public AddressBuilder setLogradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public AddressBuilder setBairro(String bairro) {
            this.bairro = bairro;
            return this;
        }

        public AddressBuilder setLocalidade(String localidade) {
            this.localidade = localidade;
            return this;
        }

        public AddressBuilder setUf(String uf) {
            this.uf = uf;
            return this;
        }

        public AddressBuilder setCep(String cep) {
            this.cep = cep;
            return this;
        }


        public Address build() {
            Address address = new Address();
            address.UID = UID;
            address.logradouro = logradouro;
            address.bairro = bairro;
            address.localidade = localidade;
            address.uf = uf;
            address.cep = cep;
            return address;
        }
    }
}
