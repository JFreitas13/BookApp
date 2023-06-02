package com.svalero.bookApp.adapter;

import static com.svalero.bookApp.db.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.bookApp.contract.DeleteFavBookContract;
import com.svalero.bookApp.db.AppDatabase;
import com.svalero.bookApp.domain.FavBook;
import com.svalero.bookapp.R;

import java.util.List;

public class FavBookAdapter extends RecyclerView.Adapter<FavBookAdapter.FavBookHolder> implements DeleteFavBookContract.View {

    private Context context;
    private List<FavBook> favBookList;
    private FavBook favBook;

    public FavBookAdapter(Context context, List<FavBook> dataList) {
        this.context = context;
        this.favBookList = dataList;

    }

    public Context getContext() {
        return context;
    }

    @Override
    public FavBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_book_item, parent, false);
        return new FavBookHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
    }

    @Override
    public void onBindViewHolder(@NonNull FavBookHolder holder, int position) {

        holder.bookName.setText(favBookList.get(position).getName());
        holder.bookYearEdition.setText(favBookList.get(position).getYearEditionString());
        holder.bookPageNumber.setText(favBookList.get(position).getPagesNumberString());
        holder.bookDescription.setText(favBookList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return favBookList.size();
    }

    public void showMessage(String message) {

    }

    public void showError(String errorMessage) {

    }

    //5.Creamos todos los componentes que tenemos
    public class FavBookHolder extends RecyclerView.ViewHolder {
        public TextView bookName;
        public TextView bookYearEdition;
        public TextView bookPageNumber;
        public TextView bookDescription;
        public Button deleteBookButton;

        public View parentView; //vista padre: recyclerView

        //constructor del holder
        public FavBookHolder(View view) {
            super(view);
            parentView = view; //Guardamos el componente padre

            bookName = view.findViewById((R.id.book_name));
            bookYearEdition = view.findViewById(R.id.book_yearEdition);
            bookPageNumber = view.findViewById(R.id.book_pageNumber);
            bookDescription = view.findViewById(R.id.book_description);
            deleteBookButton = view.findViewById(R.id.delete_book_button);

            //pulsando estos botones llamamos al metodo correspondiente
            deleteBookButton.setOnClickListener(v -> deleteBook(getAdapterPosition()));
        }
    }

    private void deleteBook(int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.are_you_sure_delete_book_message)
                .setTitle(R.string.delete_book_title)
                .setPositiveButton("Yes", (dialog, id) -> {
                    FavBook favBook = favBookList.get(position);

                    final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME) //Instanciamos la BBDD -> Pasamos el contexto para saber donde estamos
                            .allowMainThreadQueries().build();
                    db.favBookDao().delete(favBook);

                    favBookList.remove(position);
                    notifyItemRemoved(position);
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
