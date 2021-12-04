package com.sidney.buscacep.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sidney.buscacep.model.Address;
import com.sidney.buscacep.persistence.AddressRepository;

import java.util.List;

public class FavoritesActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Address>> dataSource;
    private final AddressRepository repository;

    public FavoritesActivityViewModel(Application application) {
        super(application);
        repository = new AddressRepository(application);
//        dataSource = (MutableLiveData<List<Address>>) repository.addressList();
    }

    public LiveData<List<Address>> getFavoriteList() {
        List<Address> list = repository.addressList();
        if (list == null) {
            dataSource = new MutableLiveData<>();
        } else {
            dataSource.postValue(list);
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
        repository.save(address);
    }
}
