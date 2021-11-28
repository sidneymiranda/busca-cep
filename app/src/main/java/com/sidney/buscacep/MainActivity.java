package com.sidney.buscacep;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sidney.buscacep.api.Service;
import com.sidney.buscacep.model.Address;
import com.sidney.buscacep.persistence.AddressRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Sidney Miranda
 * <p>
 * MainActivity define a visualização do conteúdo presente em activity_main.xml,
 * refere-se à tela inicial do app.
 */
public class MainActivity extends AppCompatActivity {

    private static final String ERROR = "error";
    private static final String INFO = "info";
    private Address responseAddress;
    private TextView cep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConsult = findViewById(R.id.btn_buscarCep);
        Button btnFavorites = findViewById(R.id.btn_favoritos);
        cep = findViewById(R.id.cep);

        btnConsult.setOnClickListener(view -> {
            if (cep.getText().length() == 8) {
                searchAddress();
            } else {
                Toast.makeText(MainActivity.this, "CEP inválido!", Toast.LENGTH_SHORT).show();
            }
        });

        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favorites = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(favorites);
            }
        });
    }

    private void searchAddress() {
        Call<Address> address = Service.getInstance().getWebService().findAddressByCep(cep.getText().toString());

        address.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(@NonNull Call<Address> call, @NonNull Response<Address> response) {
                if (!response.isSuccessful() || response.body().getUf() == null) {
                    Log.e(ERROR, "StatusCode:" + response.code());
                    Toast.makeText(MainActivity.this, "CEP inválido!", Toast.LENGTH_SHORT).show();
                } else {
                    responseAddress = response.body();
                    Intent result = new Intent(MainActivity.this, ResultActivity.class);
                    result.putExtra("result", responseAddress);
                    startActivity(result);
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Log.e(INFO, "Erro:" + t.getMessage());
                Toast.makeText(MainActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}