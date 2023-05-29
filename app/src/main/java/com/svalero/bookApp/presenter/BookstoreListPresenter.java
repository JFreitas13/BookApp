package com.svalero.bookApp.presenter;

import com.svalero.bookApp.contract.BookstoreListContract;
import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookApp.model.BookstoreListModel;

import java.util.List;

public class BookstoreListPresenter implements BookstoreListContract.Presenter,
    BookstoreListContract.Model.OnLoadBookstoreListener {

    private BookstoreListModel model;
    private BookstoreListContract.View view;

    public BookstoreListPresenter(BookstoreListContract.View view) {
        this.view = view;
        this.model = new BookstoreListModel();

    }

    @Override
    public void loadAllBookstores() {
        model.loadAllBookstores(this);
    }

    @Override
    public void onLoadBookstoreSuccess(List<Bookstore> bookstores) {
        view.showBookstores(bookstores);
    }

    @Override
    public void onLoadBookstoreError(String message) {
        view.showMessage(message);

    }
}
