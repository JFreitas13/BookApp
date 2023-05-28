package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.Book;

public interface ModifyBookContract {

    interface Model {
        interface OnModifyBookListener { //Creamos un listener para devolver el User modificado  si xtodo va bien o el error si va mal
            void onModifySuccess(Book book);
            void onModifyError(String message);
        }
        void modifyBook(long id, Book book, OnModifyBookListener listener);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
    }

    interface Presenter {
        void modifyBook(long id, Book book);
    }
}
