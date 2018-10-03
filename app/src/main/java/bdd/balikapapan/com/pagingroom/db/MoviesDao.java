package bdd.balikapapan.com.pagingroom.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


@Dao
public interface MoviesDao {
    String TABLE_NAME = "movies";

    @Insert
    void insert(Movies movie);

    @Query("SELECT * FROM "+TABLE_NAME)
    DataSource.Factory<Integer,Movies> getAllMovies();

    @Query("DELETE FROM "+TABLE_NAME)
    void deleteAll();


}
