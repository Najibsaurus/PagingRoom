package bdd.balikapapan.com.pagingroom.db;

import com.google.gson.annotations.SerializedName;


import java.util.List;


public class MovieResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movies> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public List<Movies> getResults() {
        return results;
    }
}
