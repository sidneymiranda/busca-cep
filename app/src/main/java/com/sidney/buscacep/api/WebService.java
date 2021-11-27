package com.sidney.buscacep.api;

import com.sidney.buscacep.model.Address;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("{cep}/json")
    Call<Address> findAddressByCep(@Path("cep") String cep);
}
