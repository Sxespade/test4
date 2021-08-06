package ru.geekbrains.githubclient.mvp.model.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.geekbrains.githubclient.mvp.model.entity.room.RoomGithubUser;

@Dao
public interface GithubUserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RoomGithubUser roomGithubUser);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<RoomGithubUser> roomGithubUsers);

    @Update
    void update(RoomGithubUser roomGithubUser);
    @Update
    void update(List<RoomGithubUser> roomGithubUsers);

    @Delete
    void delete(RoomGithubUser roomGithubUser);
    @Delete
    void delete(List<RoomGithubUser> roomGithubUsers);

    @Query("SELECT * FROM RoomGithubUser;")
    List<RoomGithubUser> getAll();

    @Query("SELECT * FROM RoomGithubUser WHERE login = :login LIMIT 1;")
    RoomGithubUser findByLogin(String login);
}
