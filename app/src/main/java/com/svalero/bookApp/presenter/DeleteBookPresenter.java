package com.svalero.bookApp.presenter;

import com.svalero.bookApp.adapter.BookAdapter;
import com.svalero.bookApp.contract.DeleteBookContract;
import com.svalero.bookApp.model.DeleteBookModel;

public class DeleteBookPresenter implements DeleteBookContract.Presenter, DeleteBookContract.Model.OnDeleteBookListener {

    private DeleteBookModel model;
    private BookAdapter view;

    public DeleteBookPresenter(BookAdapter view) {
        this.model = new DeleteBookModel();
        this.view = view;
    }

    @Override
    public void deleteBook(long bookId) {
        model.deleteBook(bookId, this);

    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Libro eliminado correctamente.");

    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar el libro. Por favor, intentalo de nuevo.");

    }
}
