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
import com.svalero.bookApp.adapter.BookstoreAdapter;
import com.svalero.bookApp.contract.BookstoreListContract;
import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookApp.presenter.BookstoreListPresenter;
import com.svalero.bookapp.R;

import java.util.ArrayList;
import java.util.List;

public class BookstoreListView extends AppCompatActivity implements BookstoreListContract.View {

    private List<Bookstore> bookstoreList;
    private BookstoreAdapter adapter;
    private BookstoreListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookstore_list_view);

        presenter = new BookstoreListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        //instanciar la lista a vacio
        bookstoreList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.bookstore_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BookstoreAdapter(this, bookstoreList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("bookstore", "Llamada desde BookstoreView");
        presenter.loadAllBookstores();

    }

    //metodo al que llama el boton de regresar al menu principal
    public void mainReturnButton(View view) {
        onBackPressed();
    }

    //crear el menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_bookstore, menu);
        return true;
    }

    //eleccion en el actionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Bookstore) {
            Intent intent = new Intent(this, AddBookstoreView.class); //para ir a otra activity
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.view_map) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    public void showBookstores(List<Bookstore> bookstores) {
        bookstoreList.clear();
        bookstoreList.addAll(bookstores);
        adapter.notifyDataSetChanged();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
