package bdd.balikapapan.com.pagingroom.network;

import bdd.balikapapan.com.pagingroom.db.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MovieService {
    @GET("discover/movie?")
    Call<MovieResponse> getMovie(@Query("api_key") String key, @Query("page") int page);
}




