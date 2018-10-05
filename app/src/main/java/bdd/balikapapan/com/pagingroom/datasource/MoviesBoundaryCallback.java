package bdd.balikapapan.com.pagingroom.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import bdd.balikapapan.com.pagingroom.BuildConfig;
import bdd.balikapapan.com.pagingroom.db.MovieResponse;
import bdd.balikapapan.com.pagingroom.db.Movies;
import bdd.balikapapan.com.pagingroom.db.MoviesRepository;
import bdd.balikapapan.com.pagingroom.network.NetworkState;
import bdd.balikapapan.com.pagingroom.network.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesBoundaryCallback extends PagedList.BoundaryCallback<Movies> implements Callback<MovieResponse> {

    private MovieService movieService;
    private MoviesRepository moviesRepository;
    private MutableLiveData<NetworkState> networkState;
    private Executor executor;
    private int PAGE = 1;

    public MoviesBoundaryCallback(MovieService movieService, MoviesRepository moviesRepository) {
        this.movieService = movieService;
        this.moviesRepository = moviesRepository;
        this.executor = Executors.newFixedThreadPool(2);
        networkState = new MutableLiveData<>();
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        executor.execute(() -> getDataMovies(PAGE));
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Movies itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        executor.execute(() -> getDataMovies(PAGE));
    }


    @Override
    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
        if (response.isSuccessful()){
            networkState.postValue(NetworkState.LOADED);

            /*
            executor.execute(() -> {

                moviesDao.insert(response.body().getResults());
            });
            */

            moviesRepository.insert(response.body().getResults());
        }
        else{
            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
        }
    }

    @Override
    public void onFailure(Call<MovieResponse> call, Throwable t) {
        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, t.getMessage()));
    }

    private void getDataMovies(int page){
        movieService.getMovie(BuildConfig.API_KEY, page).enqueue(this);
        PAGE ++;
    }
}
