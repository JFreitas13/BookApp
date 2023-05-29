package com.svalero.bookApp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.svalero.bookApp.adapter.FavBookAdapter;
import com.svalero.bookApp.contract.FavBookListContract;
import com.svalero.bookApp.domain.FavBook;
import com.svalero.bookApp.presenter.FavBookListPresenter;
import com.svalero.bookapp.R;

import java.util.ArrayList;
import java.util.List;

public class FavBookListView extends AppCompatActivity implements FavBookListContract.View {

    private List<FavBook> favBookList;
    private FavBookAdapter adapter;
    private FavBookListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_book_list_view);

        presenter = new FavBookListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        favBookList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.fav_book_list);// recreamos un onjeto RecyclerView y le pasamos el id del creado en el layout activity_team_list_view.xml
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FavBookAdapter(this, favBookList); //se lo pasamos al adapter para que pinte los datos de cada equipo de la lista en el item
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("FavBooks", "Llamada desde FavBookView");
        presenter.loadAllFavBook();

    }

    public void showFavBooks(List<FavBook> favBook) {
        favBookList.clear(); //limpiamos la lista por si tuviera datos de antes
        favBookList.addAll(favBook); //añadimos la lista que recibimos a la lista que teniamos
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
}