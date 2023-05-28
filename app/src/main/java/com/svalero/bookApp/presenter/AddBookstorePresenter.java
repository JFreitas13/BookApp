package com.svalero.bookApp.presenter;

import com.svalero.bookApp.contract.AddBookstoreContract;
import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookApp.model.AddBookstoreModel;
import com.svalero.bookApp.view.AddBookstoreView;

public class AddBookstorePresenter implements AddBookstoreContract.Presenter, AddBookstoreContract.Model.OnRegisterBookstoreListener {

    private AddBookstoreModel model;
    private AddBookstoreView view;

    public AddBookstorePresenter(AddBookstoreView view) {
        this.model = new AddBookstoreModel();
        this.view = view;
    }

    @Override
    public void addBookstore(Bookstore bookstore) {
        model.addBookstore(bookstore, this);

    }


    @Override
    public void onRegisterSuccess(Bookstore bookstore) {
        view.showMessage("La libreria " + bookstore.getName() + "se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir la libreria. Por favor, intentalo de nuevo.");

    }


}
