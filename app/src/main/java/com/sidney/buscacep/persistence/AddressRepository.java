package com.sidney.buscacep.persistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sidney.buscacep.model.Address;

import java.util.List;

public class AddressRepository {

    private final AddressDAO mAddressDao;
    private final LiveData<List<Address>> mAllAddress;

    public AddressRepository(Application application) {
        AddressRoomDatabase db = AddressRoomDatabase.getDatabase(application);
        mAddressDao = db.addressDAO();
        mAllAddress = mAddressDao.addresses();
    }

    public LiveData<List<Address>> addressList() {
        return mAllAddress;
    }

    public void save(Address address) {
        mAddressDao.save();
    }

    public void remove(Address address) {
        mAddressDao.remove();
    }

    public void removeAll() {
        mAddressDao.removeAll();
    }
}
