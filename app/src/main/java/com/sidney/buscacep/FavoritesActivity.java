package com.sidney.buscacep;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/**
 * @author Sidney Miranda
 *
 * FavorityActivity define a visualização do conteúdo presente em activity_favorites.xml,
 * refere-se à lista de endereços favoritados pelo usuário.
 */
public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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
     * retorna para ResultActivity
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

}