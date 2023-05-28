package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.FavBook;

public interface DeleteFavBookContract {

    interface Model {
        boolean deleteFavBook(FavBook favBook);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void deleteFavBook(FavBook favBook);

    }
}
