package com.svalero.bookApp.presenter;

import com.svalero.bookApp.adapter.FavBookAdapter;
import com.svalero.bookApp.contract.DeleteFavBookContract;
import com.svalero.bookApp.domain.FavBook;
import com.svalero.bookApp.model.DeleteFavBookModel;

public class DeleteFavBookPresenter implements DeleteFavBookContract.Presenter {

    private DeleteFavBookModel model;
    private FavBookAdapter view;

    public DeleteFavBookPresenter(DeleteFavBookModel model, FavBookAdapter view) {
        this.model = new DeleteFavBookModel(view.getContext());
        this.view = view;
    }

    @Override
    public void deleteFavBook(FavBook favBook) {
        model.deleteFavBook(favBook);
    }
}
