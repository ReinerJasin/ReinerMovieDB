package com.example.reinermoviedb.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reinermoviedb.R;
import com.example.reinermoviedb.model.TvShow;
import com.example.reinermoviedb.ui.main.tvShow.TvShowFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.CardViewViewHolder>{

    private Context context;
    private List<TvShow> listTvShow;

    private List<TvShow> getListTvShow() {
        return listTvShow;
    }

    public void setListTvShow(List<TvShow> listTvShow) {
        this.listTvShow = listTvShow;
    }
    public TvShowAdapter(Context context) {
        this.listTvShow = new ArrayList<TvShow>();
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new TvShowAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final TvShowAdapter.CardViewViewHolder holder, int position) {
        TvShow tvShow = getListTvShow().get(position);
        Glide.with(context).load(tvShow.getCover()).centerCrop().into(holder.img);
        holder.lbl_title.setText(tvShow.getName());
        holder.lbl_vote.setText(tvShow.getVote_average());
//        Log.d("Movie",movie.getPoster());

        holder.itemView.setOnClickListener(v -> {
            NavDirections action = TvShowFragmentDirections.actionDetailFragment(null, tvShow);
            Navigation.findNavController(v).navigate(action);
        });
    }
    @NonNull
    @Override
    public int getItemCount() {
        return getListTvShow().size();
    }


    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView lbl_title;
        TextView lbl_vote;

        CardViewViewHolder(View itemView) {
            super(itemView);
            lbl_title = itemView.findViewById(R.id.lbl_title);
            lbl_vote = itemView.findViewById(R.id.lbl_vote);
            img = itemView.findViewById(R.id.imageViewAdapter);

        }
    }
}
