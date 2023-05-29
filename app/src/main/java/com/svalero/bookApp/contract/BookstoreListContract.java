package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.Bookstore;

import java.util.List;

public interface BookstoreListContract {

    interface Model {
        interface OnLoadBookstoreListener {
            void onLoadBookstoreSuccess(List<Bookstore> bookstores);
            void onLoadBookstoreError(String message);
        }
        void loadAllBookstores(OnLoadBookstoreListener listener);
    }

    interface View {
        void showBookstores(List<Bookstore> bookstores);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllBookstores();
    }
}
