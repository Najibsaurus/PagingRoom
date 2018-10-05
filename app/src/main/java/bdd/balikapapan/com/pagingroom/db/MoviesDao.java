package bdd.balikapapan.com.pagingroom.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface MoviesDao {
    String TABLE_NAME = "movies";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movies> movie);

    @Query("SELECT * FROM "+TABLE_NAME)
    DataSource.Factory<Integer,Movies> getAllMovies();

    @Query("DELETE FROM "+TABLE_NAME)
    void deleteAll();


}
