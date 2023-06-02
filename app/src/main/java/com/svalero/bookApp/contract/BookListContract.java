package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.Book;

import java.util.List;

public interface BookListContract {

    interface Model {
        interface OnLoadBookListener {
            void onLoadBooksSuccess(List<Book> books);

            void onLoadBooksError(String message);
        }

        void loadAllBooks(OnLoadBookListener listener);
    }

    interface View {
        void showBooks(List<Book> books);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllBooks();
    }
}
