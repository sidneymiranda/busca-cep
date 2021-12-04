package com.sidney.buscacep.persistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sidney.buscacep.model.Address;

import java.util.List;

public class AddressRepository {

//    final List<Address> mAllAddress;
    private final AddressDAO mAddressDao;

    public AddressRepository(Application application) {
        AddressRoomDatabase db = AddressRoomDatabase.getDatabase(application);
        mAddressDao = db.addressDAO();
//        mAllAddress = mAddressDao.addresses();
    }

    public List<Address> addressList() {
        return mAddressDao.addresses();
    }

    public void save(Address address) {
        mAddressDao.save(address);
    }

    public void remove(Address address) {
        mAddressDao.remove(address);
    }

    public void removeAll() {
        mAddressDao.removeAll();
    }
}
