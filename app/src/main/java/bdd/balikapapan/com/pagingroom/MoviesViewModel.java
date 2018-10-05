package bdd.balikapapan.com.pagingroom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import bdd.balikapapan.com.pagingroom.datasource.MoviesBoundaryCallback;
import bdd.balikapapan.com.pagingroom.datasource.MoviesDataSourceFactory;
import bdd.balikapapan.com.pagingroom.db.Movies;
import bdd.balikapapan.com.pagingroom.db.MoviesDao;
import bdd.balikapapan.com.pagingroom.db.MoviesRepository;
import bdd.balikapapan.com.pagingroom.network.ApiClient;
import bdd.balikapapan.com.pagingroom.network.MovieService;

public class MoviesViewModel extends  AndroidViewModel {

    private MoviesRepository moviesRepository;
    MoviesDataSourceFactory moviesDataSourceFactory;

    private final int PAGE_SIZE = 20;
    private final boolean ENABLE_PLACEHOLDERS = true;
    private LiveData movieList;
    private MoviesDao moviesDao;
    private MovieService movieService;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = new MoviesRepository(application);
        moviesDao = moviesRepository.getMoviesDao();
        movieService = ApiClient.getClient().create(MovieService.class);

    }


    public LiveData<PagedList<Movies>> getAllMovies(){
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build();


        MoviesBoundaryCallback callback = new MoviesBoundaryCallback(movieService, moviesRepository);
        moviesDataSourceFactory = new MoviesDataSourceFactory();
        moviesDataSourceFactory.getMoviesLiveDataSource();


        movieList = new LivePagedListBuilder<>(moviesRepository.getAllMovies(), config).setBoundaryCallback(callback).build();
        //movieList = new LivePagedListBuilder<>(moviesDataSourceFactory, config).build();

        return movieList;
    }

    void delete(){
        moviesRepository.deleteAll();
    }
}
