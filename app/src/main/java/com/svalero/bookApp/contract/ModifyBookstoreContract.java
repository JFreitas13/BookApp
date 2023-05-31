package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.Bookstore;

public interface ModifyBookstoreContract {

    interface Model {
        interface OnModifyBookstoreListener { //Creamos un listener para devolver el Book modificado  si xtodo va bien o el error si va mal
            void onModifySuccess(Bookstore bookstore);
            void onModifyError(String message);
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
