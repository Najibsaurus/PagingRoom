package bdd.balikapapan.com.pagingroom;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bdd.balikapapan.com.pagingroom.db.Movies;

public class MoviesAdapter extends PagedListAdapter<Movies, MoviesAdapter.MoviesViewHolder> {

    private Context context;
    public MoviesAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;

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
            Glide.with(context).load(BuildConfig.BASE_URL_IMG+pos.getPoster_path()).into(moviesViewHolder.imagePoster);

        } else {
            moviesViewHolder.titleItemView.clearComposingText();
        }

    }


    class MoviesViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleItemView;
        private final ImageView imagePoster;

        private MoviesViewHolder(View itemView) {
            super(itemView);
            titleItemView = itemView.findViewById(R.id.title);
            imagePoster = itemView.findViewById(R.id.imagePoster);

        }
    }
}
