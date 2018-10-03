package bdd.balikapapan.com.pagingroom.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = MoviesDao.TABLE_NAME)

public class Movies {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "title")

    private String mTitle;

    @Ignore
    public Movies(@NonNull String mTitle){this.mTitle = mTitle;}

    public Movies(int id, @NonNull String mTitle) {
        this.id = id;
        this.mTitle = mTitle;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

}
