package com.svalero.bookApp.presenter;

import com.svalero.bookApp.contract.BookListContract;
import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.model.BookListModel;
import com.svalero.bookApp.view.BookListView;

import java.util.List;

public class BookListPresenter implements BookListContract.Presenter,
    BookListContract.Model.OnLoadBookListener {

    private BookListModel model;
    private BookListView view;

    public BookListPresenter(BookListView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadAllBooks() {
        model.loadAllBooks(this);
    }

    @Override
    public void onLoadTasksSuccess(List<Book> books) {
        view.showBooks(books);

    }

    @Override
    public void onLoadTasksError(String message) {
        view.showMessage(message);

    }


}
