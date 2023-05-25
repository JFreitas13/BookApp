package com.svalero.bookApp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.bookapp.R;

public class MainActivity extends AppCompatActivity {

    Button listBook;
    Button listBookstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listBook = findViewById(R.id.book_list);
        listBook.setOnClickListener(view ->  {
            Intent intent = new Intent(this, BookListView.class);
            startActivity(intent);
        });

//        button = findViewById(R.id.library_list);
//        button.setOnClickListener(view ->  {
//            Intent intent = new Intent(MainActivity.this, ListLibrariesActivity.class);
//            startActivity(intent);
//        });

//        button = findViewById(R.id.publisher_list);
//        button.setOnClickListener(view ->  {
//            Intent intent = new Intent(MainActivity.this, ListPublisherActivity.class);
//            startActivity(intent);
//        });
    }
}
