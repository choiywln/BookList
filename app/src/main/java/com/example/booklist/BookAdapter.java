package com.example.booklist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.booklist.databinding.ItemBooksBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    ArrayList<BookItem> BookList;

    public BookAdapter(ArrayList<BookItem> data){
        BookList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBooksBinding binding = ItemBooksBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtTitle.setText(BookList.get(position).getTitle());
        holder.binding.txtAuthor.setText(BookList.get(position).getAuthor());
        holder.binding.txtDescription.setText(BookList.get(position).getDescription());
        Log.i("POSITION", "" + position);

        Glide.with(holder.binding.imgBook)
                .load(BookList.get(position).getImage())
                .into(holder.binding.imgBook);

    }

    @Override
    public int getItemCount() {
        return BookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBooksBinding binding;

        public ViewHolder(ItemBooksBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
