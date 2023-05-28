package com.svalero.bookApp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookapp.R;

import java.util.List;

import lombok.NonNull;

public class BookstoreAdapter extends RecyclerView.Adapter<BookstoreAdapter.BookstoreHolder> {

    private Context context;
    private List<Bookstore> bookstoreList;

    public BookstoreAdapter(Context context, List<Bookstore> dataList) {
        this.context = context;
        this.bookstoreList = dataList; //lista de librerias
    }

    public Context getContext() {
        return context;
    }

    @Override
    public BookstoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookstore_item, parent, false);
        return new BookstoreHolder(view);
    }


    @Override
    public void onBindViewHolder(BookstoreHolder holder, int position) {
        holder.bookstoreName.setText(bookstoreList.get(position).getName());
        holder.bookstoreCity.setText(bookstoreList.get(position).getCity());
        holder.bookstoreZipCode.setText(bookstoreList.get(position).getZipCode());
        holder.bookstorePhoneNumber.setText(bookstoreList.get(position).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return bookstoreList.size();
    }

    public class BookstoreHolder extends RecyclerView.ViewHolder {
        public TextView bookstoreName;
        public TextView bookstoreCity;
        public TextView bookstoreZipCode;
        public TextView bookstorePhoneNumber;
        public Button modifyBookstoreButton;
        public Button deleteBookstoreButton;
        public View parentView;

        public BookstoreHolder(View view) {
            super(view);
            parentView = view;

            bookstoreName = view.findViewById((R.id.bookstore_name));
            bookstoreCity = view.findViewById(R.id.bookstore_city);
            bookstoreZipCode = view.findViewById(R.id.bookstore_zip_code);
            bookstorePhoneNumber = view.findViewById(R.id.bookstore_phone_number);
            modifyBookstoreButton = view.findViewById(R.id.modify_bookstore_button);
            deleteBookstoreButton = view.findViewById(R.id.delete_bookstore_button);

//            modifyBookstoreButton.setOnClickListener(v -> modifyBookstore(getAdapterPosition()));
//            deleteBookstoreButton.setOnClickListener(v -> deleteBookstore(getAdapterPosition()));
        }

//        private void modifyBookstore(int position) {
//            Bookstore bookstore = bookstoreList.get(position);
//
//            Intent intent = new Intent(context, ModifyBookstoreView.class);
//            intent.putExtra("libraryId", bookstore.getLibraryId());
//            context.startActivity(intent);
//        }

        //eliminar libro
//        private void deleteBookstore(int position) {
//
//            //Dialogo para confirmar que se quiere eliminar
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setMessage(R.string.are_you_sure_delete_library_message)
//                    .setTitle(R.string.delete_library_message)
//                    .setPositiveButton("Yes", (dialog, id) -> { //añadir boton de si
//
//                        //conectar BBDD
//                        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
//                                .allowMainThreadQueries().build();
//                        Library library = bookstoreList.get(position);
//                        db.libraryDao().delete(library); //borramos de la BBDD
//
//                        bookstoreList.remove(position); //borrar de la lista que se muestra
//                        notifyItemRemoved(position); //refrescar la lista sin el elemento borrado
//                    })
//                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
//            AlertDialog dialog = builder.create();
//            dialog.show();
//        }
    }
}
