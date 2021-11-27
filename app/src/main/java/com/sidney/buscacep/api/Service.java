package com.sidney.buscacep.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Service INSTANCE;
    private static final String BASE_URL = "https://viacep.com.br/ws/";
    private WebService webService;

    private Service() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webService = retrofit.create(WebService.class);
    }

    public static Service getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Service();
        }
        return INSTANCE;
    }

    public WebService getWebService() {
        return webService;
    }
}
