package com.svalero.bookApp.contract;

import com.svalero.bookApp.domain.FavBook;

import java.util.List;

public interface FavBookListContract {

    interface Model {
        List<FavBook> loadAllFavBook();
    }

    interface View {
        void showFavBooks(List<FavBook> favBook);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllFavBook();
    }
}
