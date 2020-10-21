package com.example.reinermoviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Genre implements Parcelable {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String nama;

    public Genre() {
    }

    public Genre(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    protected Genre(Parcel in) {
        id = in.readString();
        nama = in.readString();
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
    }
}
