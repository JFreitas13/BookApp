package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.Book;

import java.util.List;

public interface BookListContract {

    interface Model {
        interface OnLoadBookListener {
            void onLoadTasksSuccess(List<Book> books);
            void onLoadTasksError(String message);
        }
        void loadAllBooks(OnLoadBookListener Listener);
    }

    interface View {
        void showBooks(List<Book> books);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllBooks();
    }
}
