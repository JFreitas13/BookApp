package com.svalero.bookApp.presenter;

import com.svalero.bookApp.contract.AddBookContract;
import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.model.AddBookModel;
import com.svalero.bookApp.view.AddBookView;

public class AddBookPresenter implements AddBookContract.Presenter, AddBookContract.Model.OnRegisterBookListener {

    private AddBookModel model;
    private AddBookView view;

    public AddBookPresenter(AddBookView view) {
        this.model = new AddBookModel();
        this.view = view;
    }

    @Override
    public void addBook(Book book) {
        model.addBook(book, this);
    }


    @Override
    public void onRegisterSuccess(Book book) {
        view.showMessage("El libro " + book.getName() + "se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir el libro. Por favor, intentalo de nuevo.");

    }
}
