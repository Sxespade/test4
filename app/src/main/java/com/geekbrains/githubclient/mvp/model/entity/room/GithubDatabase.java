package com.geekbrains.githubclient.mvp.model.entity.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geekbrains.githubclient.mvp.model.entity.room.dao.GithubRepositoryDAO;
import com.geekbrains.githubclient.mvp.model.entity.room.dao.GithubUserDAO;

@Database(
    entities = {RoomGithubUser.class, RoomGithubRepository.class},
    version = 1,
    exportSchema = false)
public abstract class GithubDatabase extends RoomDatabase {
    public static final String GITHUB_DATABASE_NAME = "github-client.db";

    public abstract GithubUserDAO githubUserDAO();
    public abstract GithubRepositoryDAO githubRepositoryDAO();
}
