package com.svalero.bookApp.view;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.bookApp.contract.AddBookContract;
import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.presenter.AddBookPresenter;
import com.svalero.bookapp.R;

public class AddBookView extends AppCompatActivity implements AddBookContract.View {

    private AddBookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_view);

        Log.d("add Book", "llamada desde addBookView");

        presenter = new AddBookPresenter(this);
    }

    //boton AÑADIR
    public void addButton(View view) {
        EditText etName = findViewById(R.id.nameEditText);
        EditText etYearEdition = findViewById(R.id.yearTextNumber);
        EditText etPageNumber = findViewById(R.id.pagesTextNumber);
        EditText etDescription = findViewById(R.id.descriptionEditText);
        CheckBox cbEbook= findViewById(R.id.cbAddBookRead);

        String name = etName.getText().toString();
        String yearEditionS = etYearEdition.getText().toString();
        int yearEdition = Integer.parseInt(yearEditionS);
        String pageNumberS = etPageNumber.getText().toString();
        int pageNumber = Integer.parseInt(pageNumberS);
        String description = etDescription.getText().toString();
        boolean eBook = cbEbook.isChecked();
        Log.d("Checkbox", "Estado del checkbox: " + eBook);

        Book book = new Book(name, yearEdition, pageNumber, description, eBook);
        presenter.addBook(book);

        finish();
    }

    //boton CANCELAR
    public void cancelButton(View view) {
        onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.nameEditText)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.nameEditText)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.nameEditText)).setText("");
        ((EditText) findViewById(R.id.yearTextNumber)).setText("");
        ((EditText) findViewById(R.id.pagesTextNumber)).setText("");
        ((EditText) findViewById(R.id.descriptionEditText)).setText("");
        ((CheckBox) findViewById(R.id.cbAddBookRead)).setChecked(false);

        ((EditText) findViewById(R.id.nameEditText)).requestFocus();

    }
}