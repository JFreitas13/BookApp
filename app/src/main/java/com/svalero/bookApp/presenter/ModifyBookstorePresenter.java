package com.svalero.bookApp.presenter;

import com.svalero.bookApp.contract.ModifyBookstoreContract;
import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookApp.model.ModifyBookstoreModel;
import com.svalero.bookApp.view.ModifyBookstoreView;

public class ModifyBookstorePresenter implements ModifyBookstoreContract.Presenter, ModifyBookstoreContract.Model.OnModifyBookstoreListener {

    private ModifyBookstoreModel model;
    private ModifyBookstoreView view;

    public ModifyBookstorePresenter(ModifyBookstoreView view) {
        this.model = new ModifyBookstoreModel();
        this.view = view;
    }

    @Override
    public void modifyBookstore(long id, Bookstore bookstore) {
        model.modifyBookstore(id, bookstore, this);

    }

    @Override
    public void onModifySuccess(Bookstore bookstore) {
        view.showMessage("Libreria modificado correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar el libro. Por favor, intentalo de nuevo.");

    }
}
