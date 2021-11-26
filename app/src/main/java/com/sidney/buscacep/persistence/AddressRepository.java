package com.sidney.buscacep.persistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sidney.buscacep.model.Address;

import java.util.List;

public class AddressRepository {

    private AddressDAO mAddressDao;
    private LiveData<List<Address>> mAllAddress;

    AddressRepository(Application application) {
        AddressRoomDatabase db = AddressRoomDatabase.getDatabase(application);
        mAddressDao = db.addressDAO();
        mAllAddress = mAddressDao.addressList();
    }

    LiveData<List<Address>> addressList() {
        return mAllAddress;
    }

    void save(Address address) {
        mAddressDao.save();
    }

    void remove() {
        mAddressDao.remove();
    }

    void removeAll(Address address) {
        mAddressDao.removeAll();
    }
}
