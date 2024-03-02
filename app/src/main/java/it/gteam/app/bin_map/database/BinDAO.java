package it.gteam.app.bin_map.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.gteam.app.bin_map.model.Bin;

@Dao
public interface BinDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Bin> data);

   // @Query("DELETE FROM bins")
   // public void deleteAll();

    @Query("SELECT * FROM bins ORDER BY type DESC")
    public List<Bin> findAll();
}
