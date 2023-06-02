package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.Bookstore;

public interface ModifyBookstoreContract {

    interface Model {
        interface OnModifyBookstoreListener {
            void onModifyError(String message);

            void onModifySuccess(Bookstore bookstore);
        }

        void modifyBookstore(long id, Bookstore bookstore, OnModifyBookstoreListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyBookstore(long id, Bookstore bookstore);
    }
}
