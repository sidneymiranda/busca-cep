package com.sidney.buscacep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Sidney Miranda
 * <p>
 * MainActivity define a visualização do conteúdo presente em activity_main.xml,
 * refere-se à tela inicial do app.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConsult = findViewById(R.id.btn_buscarCep);
        TextView cep = findViewById(R.id.cep);

        btnConsult.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            if (cep.getText().length() == 8) {
                intent.putExtra("cep", cep.getText());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "CEP inválido!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}