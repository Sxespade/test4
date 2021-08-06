package ru.geekbrains.githubclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.githubclient.R;
import ru.geekbrains.githubclient.mvp.presenter.list.IRepositoriesListPresenter;
import ru.geekbrains.githubclient.mvp.view.RepositoryItemView;

public class RepositoryRVAdapter extends RecyclerView.Adapter<RepositoryRVAdapter.ViewHolder> {
    private final IRepositoriesListPresenter presenter;

    public RepositoryRVAdapter(IRepositoriesListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
            LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_repository, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.position = position;

        holder.itemView.setOnClickListener(view -> presenter.onItemClick(holder));

        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements RepositoryItemView {
        private final TextView repoName;
        private final TextView repoDescription;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.tv_repo_name);
            repoDescription = itemView.findViewById(R.id.tv_repo_description);
        }

        @Override
        public void setName(String name) {
            repoName.setText(name);
        }

        @Override
        public void setDescription(String description) {
            repoDescription.setText(description);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
