package ru.geekbrains.githubclient.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    indices = {@Index("userId")},
    foreignKeys = {
        @ForeignKey(
            entity = RoomGithubUser.class,
            parentColumns = "id",
            childColumns = "userId",
            onDelete = ForeignKey.CASCADE)
    }
)
public class RoomGithubRepository {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String description;
    private int forks;
    private String userId;

    public RoomGithubRepository(
        @NonNull String id, String name, String description, int forks, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.forks = forks;
        this.userId = userId;
    }

    @NonNull
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

    public String getUserId() {
        return userId;
    }

    public void setId(@NonNull String id) {
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

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
