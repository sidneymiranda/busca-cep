package com.sidney.buscacep.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sidney.buscacep.model.Address;
import com.sidney.buscacep.persistence.AddressRepository;

import java.util.ArrayList;
import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    private AddressRepository repository;
    private MutableLiveData<List<Address>> dataSource;

    public FavoritesViewModel(Application application) {
        super(application);
        repository = new AddressRepository(application);
    }

    public LiveData<List<Address>> getAllFavorites() {
        if (dataSource == null) {
            dataSource = new MutableLiveData<>();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Address> allFavorites = repository.getAllFavorites();
                    dataSource.postValue(allFavorites);
                }
            }).start();
        }
        return dataSource;
    }

    public void removeFavorite(Address address) {
        repository.remove(address);
    }

    public void removeAllFavorite() {
        repository.removeAll();
    }

    public void saveFavorite(Address address) {
        repository.saveFavorite(address);
    }
}
