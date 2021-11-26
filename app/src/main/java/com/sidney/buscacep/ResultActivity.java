package com.sidney.buscacep;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

/**
 * @author Sidney Miranda
 *
 * ResultActivity define a visualização do conteúdo presente em activity_result.xml,
 * refere-se à tela de resultado da busco do cpf informado na tela inicial do app.
 */
public class ResultActivity extends AppCompatActivity {

    FloatingActionButton btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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
    public boolean onOptionsItemSelected(MenuItem item) {
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
    public void showFavorites(View view) {
        Intent favorite = new Intent(ResultActivity.this, FavoritesActivity.class);
        startActivity(favorite);
    }


}