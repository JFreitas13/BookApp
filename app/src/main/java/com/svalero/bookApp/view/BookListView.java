package com.svalero.bookApp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.svalero.bookApp.adapter.BookAdapter;
import com.svalero.bookApp.contract.BookListContract;
import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.presenter.BookListPresenter;
import com.svalero.bookapp.R;

import java.util.ArrayList;
import java.util.List;

public class BookListView extends AppCompatActivity implements BookListContract.View {

    private List<Book> bookList;
    private BookAdapter adapter;
    private BookListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_view);

        presenter = new BookListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        //instanciar la lista a vacio
        bookList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.book_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BookAdapter(this, bookList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("books", "Llamada desde BookView");
        presenter.loadAllBooks();

    }

    @Override
    public void showBooks(List<Book> books) {
        bookList.clear(); //limpiamos la lista por si tuviera datos de antes
        bookList.addAll(books); //añadimos la lista que recibimos a la lista que teniamos
        adapter.notifyDataSetChanged();//notificamos al adapter de los cambios

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //metodo al que llama el boton de regresar al menu principal
    public void mainReturnButton(View view) {
        onBackPressed();
    }

    //crear el menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_book, menu);
        return true;
    }

    //eleccion en el actionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Book) {
            Intent intent = new Intent(this, AddBookView.class); //para ir a otra activity
            startActivity(intent);
            return true;
        }
        return false;
    }
}