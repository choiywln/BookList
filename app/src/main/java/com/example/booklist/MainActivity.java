package com.example.booklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.booklist.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<BookItem> BookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BookList = new ArrayList<BookItem>();
        try {
            String jsonString = readFromAssets("books.json");
            Gson gson = new Gson();
            BookList = gson.fromJson(jsonString, new TypeToken<List<BookItem>>(){}.getType());
            Log.i("TEST", "" + BookList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        BookAdapter adapter = new BookAdapter(BookList);
        binding.reyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.reyclerView.setLayoutManager(manager);
    }

    public String readFromAssets(String name) throws IOException {
        InputStream inputStream = getAssets().open(name);
        return readStream(inputStream);
    }

    public static String readStream(InputStream inputStream) {
        String contents = "";
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {

        } finally {
            contents = stringBuilder.toString().trim();
        }
        return contents;
    }
}