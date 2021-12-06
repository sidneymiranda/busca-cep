package com.sidney.buscacep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sidney.buscacep.adapter.AddressAdapter;
import com.sidney.buscacep.model.Address;
import com.sidney.buscacep.persistence.AddressRoomDatabase;
import com.sidney.buscacep.viewmodel.FavoritesViewModel;

import java.util.List;

/**
 * @author Sidney Miranda
 * <p>
 * FavorityActivity define a visualização do conteúdo presente em activity_favorites.xml,
 * refere-se à lista de endereços favoritados pelo usuário.
 */
public class FavoritesActivity extends AppCompatActivity {

    AddressRoomDatabase db;
    private AddressAdapter adapter;
    private RecyclerView recyclerView;
    private FavoritesViewModel viewModel;
    private FloatingActionButton btnSearch;
    private ImageView btRemove;
    private List<Address> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);
        btnSearch = findViewById(R.id.btn_search_address);
        btRemove = findViewById(R.id.btn_remove_address);
        recyclerView = findViewById(R.id.addressRecycleView);

        db = Room.databaseBuilder(getApplicationContext(), AddressRoomDatabase.class, "busca_cep_database")
                .allowMainThreadQueries()
                .build();
        list = db.addressDAO().addresses();


//      RecycleView
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new AddressAdapter(list, this);
        recyclerView.setAdapter(adapter);

        //        viewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
//        viewModel.getAllFavorites().observe(this, address -> adapter.update(address));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(FavoritesActivity.this, SearchAddressActivity.class);
                startActivity(search);
            }
        });
    }
}