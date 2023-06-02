package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.Book;

public interface AddBookContract {

    interface Model {
        interface OnRegisterBookListener {
            void onRegisterSuccess(Book book);

            void onRegisterError(String message);
        }

        void addBook(Book book, OnRegisterBookListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addBook(Book book);
    }
}



