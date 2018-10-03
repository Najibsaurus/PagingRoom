package bdd.balikapapan.com.pagingroom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import bdd.balikapapan.com.pagingroom.db.Movies;
import bdd.balikapapan.com.pagingroom.db.MoviesRepository;

public class MoviesViewModel extends  AndroidViewModel {

    private MoviesRepository moviesRepository;
    private final int PAGE_SIZE = 20;
    private final boolean ENABLE_PLACEHOLDERS = true;
    private LiveData<PagedList<Movies>> movieList;


    public MoviesViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = new MoviesRepository(application);
    }

    public LiveData<PagedList<Movies>> getAllMovies(){
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build();
        movieList = new LivePagedListBuilder<>(
                moviesRepository.getAllMovies(), config).build();

        return movieList;
    }

    void insert(Movies movies) { moviesRepository.insert(movies);
    }

    void delete(){
        moviesRepository.deleteAll();
    }
}
