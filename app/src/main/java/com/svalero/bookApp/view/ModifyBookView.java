package com.svalero.bookApp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.svalero.bookApp.contract.ModifyBookContract;
import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.presenter.ModifyBookPresenter;
import com.svalero.bookapp.R;

public class ModifyBookView extends AppCompatActivity implements ModifyBookContract.View {

    private long id;
    private Book book;
    private ModifyBookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_book_view);

        noticeId();

        Bundle bundle = getIntent().getExtras();
        book = (Book) bundle.getSerializable("book");
        id = book.getId();

        fillData(book);

        presenter = new ModifyBookPresenter(this);
    }


    public void modifyButton(View view) {
        EditText etName = findViewById(R.id.nameModifyEditText);
        EditText etYearEdition = findViewById(R.id.yearModifyTextNumber);
        EditText etPageNumber = findViewById(R.id.pagesModifyTextNumber);
        EditText etDescription = findViewById(R.id.descriptionModifyEditText);
        CheckBox cbRead = findViewById(R.id.checkBoxRead);

        String name = etName.getText().toString();
        String yearEditionS = etYearEdition.getText().toString();
        int yearEdition = Integer.parseInt(yearEditionS);
        String pageNumberS = etPageNumber.getText().toString();
        int pagesNumber = Integer.parseInt(pageNumberS);
        String description = etDescription.getText().toString();
        boolean eBook = cbRead.isChecked();

        Book modifiedBook = new Book(name, yearEdition, pagesNumber, description, eBook);
        presenter.modifyBook(id, modifiedBook); //metodo modificar

        finish(); //para regresar al listado una vez se confirma la modificación.

    }

    //boton cancelar y volver atras
    public void cancelModifyButton(View view) {
        onBackPressed();
    }

    //datos nuevos
    private void fillData(Book book) {
        EditText etName = findViewById(R.id.nameModifyEditText);
        EditText etYearEdition = findViewById(R.id.yearModifyTextNumber);
        EditText etPageNumber = findViewById(R.id.pagesModifyTextNumber);
        EditText etDescription = findViewById(R.id.descriptionModifyEditText);

        etName.setText(book.getName());
        etYearEdition.setText(book.getYearEditionString());
        etPageNumber.setText(book.getPagesNumberString());
        etDescription.setText(book.getDescription());
    }

    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_book_message)
                .setTitle(R.string.modify_book_title)
                .setNegativeButton("No", (dialog, id) -> { //boton de si

                    Intent intent = new Intent(this, BookListView.class);
                    intent.putExtra("id", book.getId());
                    this.startActivity(intent);
                })
                .setPositiveButton("Yes", (dialog, id) -> dialog.dismiss()); //boton del no
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }
}