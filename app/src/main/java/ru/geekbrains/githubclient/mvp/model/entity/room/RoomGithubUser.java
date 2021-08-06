package ru.geekbrains.githubclient.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index("login")})
public class RoomGithubUser {
    @PrimaryKey
    @NonNull
    private String id;
    private String login;
    private String avatarUrl;
    private String reposUrl;

    public RoomGithubUser(@NonNull String id, String login, String avatarUrl, String reposUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.reposUrl = reposUrl;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }
}
