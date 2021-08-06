package ru.geekbrains.githubclient.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class GithubRepository implements Parcelable {
    @Expose private String id;
    @Expose private String name;
    @Expose private String description;
    @Expose private int forks;

    public GithubRepository(String id, String name, String description, int forks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.forks = forks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getForks() {
        return forks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }


    // Parcelization code below
    protected GithubRepository(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        forks = in.readInt();
    }

    public static final Creator<GithubRepository> CREATOR = new Creator<GithubRepository>() {
        @Override
        public GithubRepository createFromParcel(Parcel in) {
            return new GithubRepository(in);
        }

        @Override
        public GithubRepository[] newArray(int size) {
            return new GithubRepository[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(forks);
    }
}
