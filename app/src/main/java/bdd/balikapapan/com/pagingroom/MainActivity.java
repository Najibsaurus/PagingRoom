package bdd.balikapapan.com.pagingroom;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import bdd.balikapapan.com.pagingroom.db.Movies;


public class MainActivity extends AppCompatActivity {

    private MoviesViewModel moviesViewModel;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        final MoviesAdapter adapter = new MoviesAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getAllMovies().observe(this, movies -> {
            recyclerView.setAdapter(adapter);
            adapter.submitList(movies);
        });


    }
}
