package com.svalero.bookApp.presenter;

import com.svalero.bookApp.contract.FavBookListContract;
import com.svalero.bookApp.domain.FavBook;
import com.svalero.bookApp.model.FavBookListModel;
import com.svalero.bookApp.view.FavBookListView;

import java.util.List;

public class FavBookListPresenter implements FavBookListContract.Presenter{

    private FavBookListModel model;
    private FavBookListView view;

    public FavBookListPresenter(FavBookListView view) {
        this.view = view;
        this.model = new FavBookListModel(view.getApplicationContext());
    }

    @Override
    public void loadAllFavBook() {
        List<FavBook> favBook  = model.loadAllFavBook();
        view.showFavBooks(favBook);

    }
}
