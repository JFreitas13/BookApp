package com.svalero.bookApp.presenter;

import com.svalero.bookApp.adapter.BookAdapter;
import com.svalero.bookApp.contract.AddFavBookContract;
import com.svalero.bookApp.domain.FavBook;
import com.svalero.bookApp.model.AddFavBookModel;

public class AddFavBookPresenter implements AddFavBookContract.Presenter {

    private AddFavBookModel model;
    private BookAdapter view;

    public AddFavBookPresenter(BookAdapter view) {
        this.view = view;
        this.model = new AddFavBookModel(view.getContext());
    }

    @Override
    public void addFavBook(FavBook favBook) {
        boolean done = model.addFavBook(favBook); //devolvemenos un true o false en funcion de lo que nos devuielva el model al intentar registrar
        if (done) {
            view.showMessage("Libro añadido a Favoritos"); //Mensajes que le devolvemos a la view
        } else {
            view.showError("No se ha podido añadir el libro a Favoritos. Intentalo más tarde.");
        }
    }
}
