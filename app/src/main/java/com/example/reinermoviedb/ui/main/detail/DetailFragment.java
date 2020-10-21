package com.example.reinermoviedb.ui.main.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reinermoviedb.R;
import com.example.reinermoviedb.model.Genre;
import com.example.reinermoviedb.model.Movie;
import com.example.reinermoviedb.model.TvShow;
import com.example.reinermoviedb.ui.MainActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    @BindView(R.id.imageViewAdapter)
    ImageView background;

    @BindView(R.id.detail_title)
    TextView title;

    @BindView(R.id.detail_year)
    TextView year;

    @BindView(R.id.detail_genre)
    TextView genre;

    @BindView(R.id.detail_adult)
    TextView adult;

    @BindView(R.id.detail_description)
    TextView description;

    @BindView(R.id.detail_cast)
    TextView cast;

    @BindView(R.id.imageView2)
    ImageView cover;

    @BindView(R.id.detail_vote)
    TextView vote;

    @BindView(R.id.rv_cast)
    RecyclerView rv;

    private Movie movie;
    private TvShow tvShow;
    private DetailViewModel viewModel;
    private DetailCastAdapter adapter;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);

        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new DetailCastAdapter(getActivity());

        if (getArguments() != null) {
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTvShow();

            if (movie != null) {
                initMovie(movie);
                observeMovieViewModel(Integer.parseInt(movie.getId_movie()));
            } else {
                initShow(tvShow);
                observeShowViewModel(Integer.parseInt(tvShow.getId_show()));
            }

        }

//        button.setOnClickListener(v -> {
//            NavDirections action = DetailFragmentDirections.actionMovieFragment();
//            Navigation.findNavController(view).navigate(action);
//        });
    }

    private void observeShowViewModel(int idShow) {
        viewModel.getTvShowGenre(idShow).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        genre.append(g.getNama() + "|");
                    } else {
                        genre.append(g.getNama());
                    }
                }
            }
        });

        viewModel.getShowCast(idShow).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv.setAdapter(adapter);
            }
        });
    }

    private void observeMovieViewModel(int idMovie) {
        viewModel.getMovieGenre(idMovie).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        genre.append(g.getNama() + " | ");
                    } else {
                        genre.append(g.getNama());
                    }
                }
            }
        });
        viewModel.getMovieCast(idMovie).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv.setAdapter(adapter);
            }
        });
    }

    private void initShow(TvShow tvShow) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(tvShow.getName());
        Glide.with(getActivity()).load(tvShow.getPoster()).centerCrop().into(cover);
        Glide.with(getActivity()).load(tvShow.getPoster()).centerCrop().into(background);
        adult.setVisibility(View.INVISIBLE);
        title.setText(tvShow.getName());
        vote.setText(tvShow.getVote_average());
        description.setText(tvShow.getDescription());
    }

    private void initMovie(Movie movie) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(movie.getTitle());
        Glide.with(getActivity()).load(movie.getPoster()).into(cover);
        Glide.with(getActivity()).load(movie.getPoster()).into(background);
        adult.setVisibility(View.VISIBLE);
        if (movie.getAdult().equalsIgnoreCase("false")) {
            adult.setText("All age");
        } else {
            adult.setText("Adult");
        }
        title.setText(movie.getTitle());
        vote.setText(movie.getVote_average());
        description.setText(movie.getDescription());
    }

}