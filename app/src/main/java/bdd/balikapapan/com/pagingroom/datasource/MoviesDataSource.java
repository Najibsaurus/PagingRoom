package bdd.balikapapan.com.pagingroom.datasource;


import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import bdd.balikapapan.com.pagingroom.BuildConfig;
import bdd.balikapapan.com.pagingroom.db.MovieResponse;
import bdd.balikapapan.com.pagingroom.db.Movies;
import bdd.balikapapan.com.pagingroom.network.ApiClient;
import bdd.balikapapan.com.pagingroom.network.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDataSource  extends  PageKeyedDataSource<Integer,Movies>{
    MovieService api = ApiClient.getClient().create(MovieService.class);
    private static final int FIRST_PAGE = 1;



    @Override
    public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<Integer> params, @NonNull PageKeyedDataSource.LoadInitialCallback<Integer, Movies> callback) {
        Call<MovieResponse> call = api.getMovie(BuildConfig.API_KEY,FIRST_PAGE);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response != null) {
                    callback.onResult(response.body().getResults(),FIRST_PAGE,FIRST_PAGE+1);

                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull PageKeyedDataSource.LoadParams<Integer> params, @NonNull PageKeyedDataSource.LoadCallback<Integer, Movies> callback) {
        Call<MovieResponse> call = api.getMovie(BuildConfig.API_KEY,params.key);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                  Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.isSuccessful() && response != null) {
                    callback.onResult(response.body().getResults(),adjacentKey);
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull PageKeyedDataSource.LoadParams<Integer> params, @NonNull PageKeyedDataSource.LoadCallback<Integer, Movies> callback) {
        Call<MovieResponse> call = api.getMovie(BuildConfig.API_KEY,params.key);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
              //  Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.isSuccessful() && response != null) {
                    callback.onResult(response.body().getResults(),params.key+1);
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }
}
