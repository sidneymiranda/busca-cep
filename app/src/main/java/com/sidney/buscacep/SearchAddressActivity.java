package com.sidney.buscacep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sidney.buscacep.api.Service;
import com.sidney.buscacep.model.Address;
import com.sidney.buscacep.persistence.AddressRepository;
import com.sidney.buscacep.persistence.AddressRoomDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Sidney Miranda
 * <p>
 * SearchAddressActivity define a visualização do conteúdo presente em activity_main.xml,
 * refere-se à tela inicial do app.
 */
public class SearchAddressActivity extends AppCompatActivity {

    private static final String ERROR = "error";
    private static final String INFO = "info";

    private boolean favorite = false;
    private Address responseAddress;
    private ImageView btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_address);

        Button btnConsult = findViewById(R.id.btn_search_cep);
        ImageView btnFavorites = findViewById(R.id.btn_favorite);
        TextView cep = findViewById(R.id.cep);
        FloatingActionButton goFavorites = findViewById(R.id.spy_favorite);
        btnFavorite = findViewById(R.id.btn_favorite);

        btnConsult.setOnClickListener(view -> {
            if (cep.getText().length() == 8) {
                hideKeyboard(getApplicationContext(), cep);
                searchAddress(cep.getText().toString());
            } else if (cep.getText().toString().equals("")) {
                Toast.makeText(SearchAddressActivity.this, "Informe o CEP!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SearchAddressActivity.this, "CEP inválido!", Toast.LENGTH_SHORT).show();
            }
        });

        btnFavorites.setOnClickListener(view -> {
            if (!favorite && responseAddress != null) {
                saveFavorite();
            } else {
                removeFavorite();
            }
        });

        goFavorites.setOnClickListener(v -> startActivity(
                new Intent(SearchAddressActivity.this, FavoritesActivity.class)));
    }

    /**
     * Método responsável por consumir a API externa para consultar os
     * endereços
     *
     * @param cep corresponde aos números informado pelo usuário
     */
    private void searchAddress(String cep) {
        Call<Address> address = Service.getInstance().getWebService().findAddressByCep(cep);
        address.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(@NonNull Call<Address> call, @NonNull Response<Address> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.e(ERROR, "StatusCode:" + response.code());
                    Toast.makeText(SearchAddressActivity.this, "CEP inválido!", Toast.LENGTH_SHORT).show();
                } else {
                    View card = findViewById(R.id.card_result);
                    if (card.getVisibility() == View.GONE) {
                        card.setVisibility(View.VISIBLE);
                    } else {
                        clearResult();
                    }
                    responseAddress = response.body();
                    loadResult(responseAddress);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Address> call, @NonNull Throwable t) {
                Log.e(INFO, "Erro:" + t.getMessage());
                Toast.makeText(SearchAddressActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Método responável por carregar o resultado da busca por CEP
     *
     * @param address corresponde ao objeto do endereço retornado
     */
    private void loadResult(Address address) {
        if (address != null) {
            TextView rua = findViewById(R.id.rua);
            TextView bairro = findViewById(R.id.bairro);
            TextView cidade = findViewById(R.id.cidade);
            TextView estado = findViewById(R.id.uf);

            rua.setText(address.getLogradouro());
            bairro.setText(address.getBairro());
            cidade.setText(address.getLocalidade());
            estado.setText(address.getUf());
        }
    }

    /**
     * Metódo responsável por limpar os campos de exibição do resultado da busca por CEP
     */
    private void clearResult() {
        TextView rua = findViewById(R.id.rua);
        TextView bairro = findViewById(R.id.bairro);
        TextView cidade = findViewById(R.id.cidade);
        TextView estado = findViewById(R.id.uf);

        rua.setText("");
        bairro.setText("");
        cidade.setText("");
        estado.setText("");
        btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_star_border_24, getTheme()));
    }

    /**
     * Método responsável por esconder o teclado
     */
    private void hideKeyboard(@NonNull Context context, @NonNull View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void saveFavorite() {
        favorite = true;
        Address address = Address.AddressBuilder.builder()
                .setUID(null)
                .setLogradouro(responseAddress.getLogradouro())
                .setBairro(responseAddress.getBairro())
                .setLocalidade(responseAddress.getLocalidade())
                .setUf(responseAddress.getUf())
                .setCep(responseAddress.getCep())
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                AddressRepository repo = new AddressRepository(getApplication());
                repo.saveFavorite(address);
            }
        }).start();

        btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_star_rate_24, getTheme()));
        Toast.makeText(SearchAddressActivity.this, "Endereço salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void removeFavorite() {
        favorite = false;
        btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_star_border_24, getTheme()));
        Toast.makeText(SearchAddressActivity.this, "Removido dos favoritos!", Toast.LENGTH_SHORT).show();
    }

}