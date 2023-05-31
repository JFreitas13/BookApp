package com.svalero.bookApp.presenter;

import com.svalero.bookApp.adapter.BookAdapter;
import com.svalero.bookApp.adapter.BookstoreAdapter;
import com.svalero.bookApp.contract.DeleteBookContract;
import com.svalero.bookApp.contract.DeleteBookstoreContract;
import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookApp.model.DeleteBookModel;
import com.svalero.bookApp.model.DeleteBookstoreModel;

public class DeleteBookstorePresenter implements DeleteBookstoreContract.Presenter, DeleteBookstoreContract.Model.OnDeleteBookstoreListener {

    private DeleteBookstoreModel model;
    private BookstoreAdapter view;

    public DeleteBookstorePresenter(BookstoreAdapter view) {
        this.model = new DeleteBookstoreModel();
        this.view = view;
    }

    @Override
    public void deleteBookstore(long id) {
        model.deleteBookstore(id, this);

    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Libreria eliminada correctamente.");

    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar la libreria. Por favor, intentalo de nuevo.");

    }


}
