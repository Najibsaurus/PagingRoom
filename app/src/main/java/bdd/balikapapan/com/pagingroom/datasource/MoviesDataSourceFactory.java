package bdd.balikapapan.com.pagingroom.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import bdd.balikapapan.com.pagingroom.db.Movies;

public class MoviesDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, Movies>> movieLiveDataSource = new MutableLiveData<>();
    @Override
    public DataSource<Integer, Movies> create() {

        MoviesDataSource moviesDataSource = new MoviesDataSource();
        movieLiveDataSource.postValue(moviesDataSource);
        return moviesDataSource;
    }
    public MutableLiveData<PageKeyedDataSource<Integer, Movies>> getMoviesLiveDataSource() {
        return movieLiveDataSource;
    }
}
