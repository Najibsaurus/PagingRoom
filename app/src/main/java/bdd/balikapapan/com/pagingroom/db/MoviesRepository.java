package bdd.balikapapan.com.pagingroom.db;

import android.app.Application;
import android.arch.paging.DataSource;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class MoviesRepository  {
    private MoviesDao moviesDao;

    private DataSource.Factory<Integer, Movies> mAllMovies;

    public MoviesRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDabase(application);
        moviesDao = db.moviesDao();
        mAllMovies = moviesDao.getAllMovies();
    }


    public MoviesDao getMoviesDao() {
        return moviesDao;
    }
    public DataSource.Factory<Integer,Movies> getAllMovies() {return  mAllMovies;}

    public void insert(List<Movies> movies) {
        new insertAsyncTask(moviesDao).execute(movies);
    }
    public void deleteAll(){
        new deleteAsyncTask(moviesDao).execute((Object) null);
    }


    private static class deleteAsyncTask extends AsyncTask<Object, Void, Void> {
        private MoviesDao mAsyncTaskDao;

        deleteAsyncTask(MoviesDao dao) {
            mAsyncTaskDao = dao; }
        @Override
        protected Void doInBackground(Object[] objects) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }


    private static class insertAsyncTask extends AsyncTask<List<Movies>,Void, Void> {

        private MoviesDao mAsyncTaskDao;
        insertAsyncTask(MoviesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<Movies>... lists) {
            mAsyncTaskDao.insert(lists[0]);
            return null;
        }
    }

}
