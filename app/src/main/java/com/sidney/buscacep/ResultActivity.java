package com.sidney.buscacep;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sidney.buscacep.model.Address;

import java.util.Objects;

/**
 * @author Sidney Miranda
 * <p>
 * ResultActivity define a visualização do conteúdo presente em activity_result.xml,
 * refere-se à tela de resultado da busco do cpf informado na tela inicial do app.
 */
public class ResultActivity extends AppCompatActivity {

    private static final String ERROR = "error";
    private static final String INFO = "info";
    private boolean favorite = false;
    private ImageView btnFavorite;
    private Address responseAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        loadResult();

        btnFavorite = findViewById(R.id.btnFavorite);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFavorite(v);
            }
        });
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
        handleFavorite();
//        AddressRepository db = new AddressRepository(getApplication());
//
//        Address address = Address.AddressBuilder.builder()
//                .setUID(0l)
//                .setLogradouro(responseAddress.getLogradouro())
//                .setBairro(responseAddress.getBairro())
//                .setLocalidade(responseAddress.getLocalidade())
//                .setUf(responseAddress.getUf())
//                .build();
//
//        db.save(address);
    }


    private void loadResult() {
        if (getIntent().hasExtra("result")) {
            TextView rua = findViewById(R.id.rua);
            TextView bairro = findViewById(R.id.bairro);
            TextView cidade = findViewById(R.id.cidade);
            TextView estado = findViewById(R.id.estado);

            Bundle extras = getIntent().getExtras();
            responseAddress = (Address) extras.get("result");

            rua.setText(responseAddress.getLogradouro());
            bairro.setText(responseAddress.getBairro());
            cidade.setText(responseAddress.getLocalidade());
            estado.setText(responseAddress.getUf());
        }
    }

    private void handleFavorite() {
        if (!favorite) {
            favorite = true;
            btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_star_rate_24, getTheme()));
        } else {
            btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_star_border_24, getTheme()));
            favorite = false;
        }
    }

}