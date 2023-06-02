package com.svalero.bookApp.contract;


import com.svalero.bookApp.domain.Bookstore;

public interface AddBookstoreContract {

    interface Model {
        interface OnRegisterBookstoreListener {
            void onRegisterSuccess(Bookstore bookstore);

            void onRegisterError(String message);
        }

        void addBookstore(Bookstore bookstore, OnRegisterBookstoreListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addBookstore(Bookstore bookstore);
    }
}



