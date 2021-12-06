package com.sidney.buscacep.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sidney.buscacep.model.Address;

import java.util.List;

/**
 * @author Sidney Miranda
 */

@Dao
public interface AddressDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void save(Address... addresses);

    @Delete
    int remove(Address addresses);

    @Query("DELETE FROM address_table")
    void removeAll();

    @Query("SELECT * FROM address_table")
    List<Address> addresses();

    @Query("SELECT * FROM address_table WHERE cep = :cep")
    Address findByCep(String cep);
}
