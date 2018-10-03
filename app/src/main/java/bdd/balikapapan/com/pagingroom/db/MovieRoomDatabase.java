package bdd.balikapapan.com.pagingroom.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Movies.class}, version = 1)
public abstract class MovieRoomDatabase extends RoomDatabase {
    public abstract MoviesDao moviesDao();

    private static volatile  MovieRoomDatabase INSTANCE;

    static MovieRoomDatabase getDabase(final Context context){
        if (INSTANCE == null){
            synchronized (MovieRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),MovieRoomDatabase.class, "movie_database").build();
                }
            }
        }


        return INSTANCE;
    }
}
