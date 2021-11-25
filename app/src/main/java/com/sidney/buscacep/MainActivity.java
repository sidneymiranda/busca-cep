package com.sidney.buscacep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String CPF = "";
    private Button btnConsult;
    private TextView cep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConsult = findViewById(R.id.btn_buscarCep);
        cep = findViewById(R.id.cep);

        btnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent = new Intent(MainActivity.this, ResultActivity.class);
                itent.putExtra(CPF, cep.getText().toString());
                startActivity(itent);
            }
        });
    }
}