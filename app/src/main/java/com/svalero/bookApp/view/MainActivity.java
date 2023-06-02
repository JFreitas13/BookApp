package com.svalero.bookApp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.svalero.bookapp.R;

public class MainActivity extends AppCompatActivity {

    Button listBook;
    Button listBookstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listBook = findViewById(R.id.book_list);
        listBook.setOnClickListener(view -> {
            Intent intent = new Intent(this, BookListView.class);
            startActivity(intent);
        });

        listBookstore = findViewById(R.id.bookstore_list);
        listBookstore.setOnClickListener(view -> {
            Intent intent = new Intent(this, BookstoreListView.class);
            startActivity(intent);
        });
    }
}
