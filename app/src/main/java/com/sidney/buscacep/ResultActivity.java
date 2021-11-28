package com.sidney.buscacep;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sidney.buscacep.api.Service;
import com.sidney.buscacep.model.Address;
import com.sidney.buscacep.persistence.AddressRepository;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Sidney Miranda
 * <p>
 * ResultActivity define a visualização do conteúdo presente em activity_result.xml,
 * refere-se à tela de resultado da busco do cpf informado na tela inicial do app.
 */
public class ResultActivity extends AppCompatActivity {

    private static final String ERROR = "error";
    private static final String INFO = "info";

    private FloatingActionButton btnFavorite;
    private Address responseAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        loadResult();

        btnFavorite = findViewById(R.id.btnFavorite);
    }

    /**
     * Boatão de retorno padrão do android
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Método responsável por processar o evento de click no item do menu "voltar" e
     * retorna para MainActivity
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Criar opções no menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /**
     * Método que chama a activity dos endereços favoritados
     */
    public void saveFavorite(View view) {
        AddressRepository db = new AddressRepository(getApplication());

        Address address = Address.AddressBuilder.builder()
                .setUID(0l)
                .setLogradouro(responseAddress.getLogradouro())
                .setBairro(responseAddress.getBairro())
                .setLocalidade(responseAddress.getLocalidade())
                .setUf(responseAddress.getUf())
                .build();

        db.save(address);
    }

    private void loadResult() {
        if (getIntent().hasExtra("cep")) {
            Bundle extras = getIntent().getExtras();
            Log.i(extras.getString("cep"), extras.toString());

            Call<Address> address = Service.getInstance().getWebService().findAddressByCep(extras.get("cep").toString());

            address.enqueue(new Callback<Address>() {
                @Override
                public void onResponse(@NonNull Call<Address> call, @NonNull Response<Address> response) {
                    if (!response.isSuccessful()) {
                        Log.e(ERROR, "StatusCode:" + response.code());
                        Toast.makeText(ResultActivity.this, "CEP inválido!", Toast.LENGTH_SHORT).show();
                    } else {
                        responseAddress = response.body();

                        TextView rua = findViewById(R.id.rua);
                        TextView bairro = findViewById(R.id.bairro);
                        TextView cidade = findViewById(R.id.cidade);
                        TextView estado = findViewById(R.id.estado);

                        rua.setText(responseAddress.getLogradouro());
                        bairro.setText(responseAddress.getBairro());
                        cidade.setText(responseAddress.getLocalidade());
                        estado.setText(responseAddress.getUf());
                    }
                }

                @Override
                public void onFailure(Call<Address> call, Throwable t) {
                    Log.e(INFO, "Erro:" + t.getMessage());
                    Toast.makeText(ResultActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}