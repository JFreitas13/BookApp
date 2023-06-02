package com.svalero.bookApp.contract;

public interface DeleteBookstoreContract {

    interface Model {
        interface OnDeleteBookstoreListener {
            void onDeleteSuccess();

            void onDeleteError(String message);
        }

        void deleteBookstore(long id, OnDeleteBookstoreListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deleteBookstore(long bookId);
    }
}
