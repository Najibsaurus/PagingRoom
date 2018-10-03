package bdd.balikapapan.com.pagingroom;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bdd.balikapapan.com.pagingroom.db.Movies;

public class MoviesAdapter extends PagedListAdapter<Movies, MoviesAdapter.MoviesViewHolder> {


    public MoviesAdapter() {
        super(DIFF_CALLBACK);
    }


    private static DiffUtil.ItemCallback<Movies> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movies>() {

                @Override
                public boolean areItemsTheSame(Movies oldData, Movies newData) {
                    return oldData.getId() == newData.getId();
                }
                @Override
                public boolean areContentsTheSame(Movies oldData, @NonNull Movies newData) {
                    return oldData.equals(newData);
                }
            };

    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item,viewGroup, false);
        return new MoviesViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder moviesViewHolder, int i) {
        Movies pos = getItem(i);
        if (pos != null){
            moviesViewHolder.titleItemView.setText(pos.getTitle());
        } else {
            moviesViewHolder.titleItemView.clearComposingText();
        }

    }


     class MoviesViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleItemView;

        private MoviesViewHolder(View itemView) {
            super(itemView);
            titleItemView = itemView.findViewById(R.id.title);
        }
    }
}
