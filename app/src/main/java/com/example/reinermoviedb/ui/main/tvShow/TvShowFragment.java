package com.example.reinermoviedb.ui.main.tvShow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reinermoviedb.R;
import com.example.reinermoviedb.adapter.MovieAdapter;
import com.example.reinermoviedb.adapter.TvShowAdapter;
import com.example.reinermoviedb.model.Movie;
import com.example.reinermoviedb.model.TvShow;
import com.example.reinermoviedb.ui.main.tvShow.TvShowViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private TvShowViewModel viewModel;
    private TvShowAdapter adapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = new TvShowAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel = ViewModelProviders.of(requireActivity()).get(TvShowViewModel.class);
        viewModel.getTvShowCollection().observe(requireActivity(), observeViewModel);
        Movie movie = new Movie();

//        button.setOnClickListener(v -> {
//            NavDirections action = MovieFragmentDirections.actionDetailFragment(movie);
//            Navigation.findNavController(view).navigate(action);
//        });
    }

    private Observer<List<TvShow>> observeViewModel = tvshows -> {
        if (tvshows != null){
//            Movie movie = movies.get(0);
//            button.setText(movie.getTitle());
//            Toast.makeText(requireActivity(), movie.getTitle(), Toast.LENGTH_SHORT).show();
            // set adapter
            adapter.setListTvShow(tvshows);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
            // add adapter ro recyclerview
        }
    };
}