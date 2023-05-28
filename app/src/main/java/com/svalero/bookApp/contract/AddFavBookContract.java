package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.FavBook;

public interface AddFavBookContract {

    interface Model {
        boolean addFavBook(FavBook favBook);
    }

    interface View {
        void showError (String errorMessage);
        void showMessage (String message);
    }

    interface Presenter {
        void addFavBook(FavBook favBook);

    }
}
