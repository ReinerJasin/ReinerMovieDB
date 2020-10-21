package com.example.reinermoviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.reinermoviedb.util.Constant;
import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    private String cover;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String description;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private String vote_average;

    public Movie(String popularity, String poster, String cover, String title, String description, String releaseDate, String vote_average) {
        this.popularity = popularity;
        this.poster = poster;
        this.cover = cover;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.vote_average = vote_average;
    }

    public Movie() {

    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster() {
        return Constant.IMG_BASE_URL + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.popularity);
        dest.writeString(this.poster);
        dest.writeString(this.cover);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.releaseDate);
        dest.writeString(this.vote_average);
    }

    protected Movie(Parcel in) {
        this.popularity = in.readString();
        this.poster = in.readString();
        this.cover = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.releaseDate = in.readString();
        this.vote_average = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
