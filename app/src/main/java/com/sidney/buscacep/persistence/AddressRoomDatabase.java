package com.sidney.buscacep.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sidney.buscacep.model.Address;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sidney Miranda
 */

@Database(entities = {Address.class}, version = 1, exportSchema = false)
public abstract class AddressRoomDatabase extends RoomDatabase {
    abstract AddressDAO addressDAO();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile AddressRoomDatabase INSTANCE;

//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            databaseWriteExecutor.execute(() -> {
//                AddressDAO dao = INSTANCE.addressDAO();
//                dao.removeAll();
//            });
//        }
//    };

    static AddressRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AddressRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AddressRoomDatabase.class, "busca_cep_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }




}
