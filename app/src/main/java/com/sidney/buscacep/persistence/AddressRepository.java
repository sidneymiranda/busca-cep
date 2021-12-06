package com.sidney.buscacep.persistence;

import android.app.Application;

import com.sidney.buscacep.model.Address;

import java.util.List;

public class AddressRepository {

    private List<Address> mAllAddress;
    private AddressDAO mAddressDao;

    public AddressRepository(Application application) {
        AddressRoomDatabase db = AddressRoomDatabase.getDatabase(application);
        mAddressDao = db.addressDAO();
        mAllAddress = mAddressDao.addresses();
    }

    public List<Address> getAllFavorites() {
        return mAllAddress;
    }

    public Address findAddressInDatabase(String cep) {
        return mAddressDao.findByCep(cep);
    }

    public void saveFavorite(Address address) {
        AddressRoomDatabase.databaseWriteExecutor.execute(() -> mAddressDao.save(address));
    }

    public void remove(Address address) {
        mAddressDao.remove(address);
    }

    public void removeAll() {
        mAddressDao.removeAll();
    }
}
