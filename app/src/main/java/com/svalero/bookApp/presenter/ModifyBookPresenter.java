package com.svalero.bookApp.presenter;

import com.svalero.bookApp.contract.ModifyBookContract;
import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.model.ModifyBookModel;
import com.svalero.bookApp.view.ModifyBookView;

public class ModifyBookPresenter implements ModifyBookContract.Presenter, ModifyBookContract.Model.OnModifyBookListener {

    private ModifyBookModel model;
    private ModifyBookView view;

    public ModifyBookPresenter(ModifyBookView view) {
        this.model = new ModifyBookModel();
        this.view = view;
    }

    @Override
    public void modifyBook(long id, Book book) {
        model.modifyBook(id, book, this);

    }

    @Override
    public void onModifySuccess(Book book) {
        view.showMessage("Libro modificado correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar el libro. Por favor, intentalo de nuevo.");

    }
}
