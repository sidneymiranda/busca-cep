package com.sidney.buscacep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sidney.buscacep.adapter.AddressAdapter;
import com.sidney.buscacep.model.Address;
import com.sidney.buscacep.viewmodel.FavoritesActivityViewModel;

import java.util.List;

/**
 * @author Sidney Miranda
 * <p>
 * FavorityActivity define a visualização do conteúdo presente em activity_favorites.xml,
 * refere-se à lista de endereços favoritados pelo usuário.
 */
public class FavoritesActivity extends AppCompatActivity {

    private static final String NO_DATA = "Não há endereços salvos!";
    private AddressAdapter adapter;
    private RecyclerView recyclerView;
    private FavoritesActivityViewModel viewModel;
    private ImageView btRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);

        FloatingActionButton btnSearch = findViewById(R.id.btn_search_address);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(FavoritesActivity.this, SearchAddressActivity.class);
                startActivity(search);
            }
        });

        btRemove = findViewById(R.id.btn_remove_address);

//      RecycleView
        recyclerView = findViewById(R.id.addressRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressAdapter(this);
        recyclerView.setAdapter(adapter);

//      ViewModel
        viewModel = new ViewModelProvider(this).get(FavoritesActivityViewModel.class);
//        viewModel.getFavoriteList().observe(FavoritesActivity.this, addresses -> {
//            if (addresses == null || addresses.size() == 0) {
//                Toast.makeText(FavoritesActivity.this, NO_DATA, Toast.LENGTH_SHORT).show();
//            } else {
//                adapter.setAddressList(addresses);
//            }
//        });
    }

    private void removeAddress(Address address) {
        viewModel.removeFavorite(address);
    }
}