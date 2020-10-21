package com.example.reinermoviedb.ui.main.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reinermoviedb.model.TvShow;
import com.example.reinermoviedb.repository.TvShowRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {

    private TvShowRepository repository;

    public TvShowViewModel() {
        repository = TvShowRepository.getInstance();
    }

    public LiveData<List<TvShow>> getTvShowCollection(){
        return repository.getTvShowCollection();
    }
}
