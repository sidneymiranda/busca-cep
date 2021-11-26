package com.sidney.buscacep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Sidney Miranda
 *
 * MainActivity define a visualização do conteúdo presente em activity_main.xml,
 * refere-se à tela inicial do app.
 */
public class MainActivity extends AppCompatActivity {

    public static final String CPF = "";
    private TextView cep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConsult = findViewById(R.id.btn_buscarCep);
        cep = findViewById(R.id.cep);

        btnConsult.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra(CPF, cep.getText().toString());
            startActivity(intent);
        });
    }
}