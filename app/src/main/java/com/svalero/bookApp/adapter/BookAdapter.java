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

import com.svalero.bookApp.contract.AddFavBookContract;
import com.svalero.bookApp.contract.DeleteBookContract;
import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.domain.FavBook;
import com.svalero.bookApp.presenter.AddFavBookPresenter;
import com.svalero.bookApp.presenter.DeleteBookPresenter;
import com.svalero.bookApp.view.ModifyBookView;
import com.svalero.bookapp.R;

import java.util.List;

import lombok.NonNull;

//Indicamos a Android lo que debe pintar en el ReclyclerView. Usamos el patron Holder
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> implements DeleteBookContract.View, AddFavBookContract.View {

    private Context context; // Activity en la que estamos
    private List<Book> bookList;
    private View snackBarView;
    private DeleteBookPresenter presenter;
    private AddFavBookPresenter favPresenter;

    //1. constructor que creamos para pasarle los datos que queremos que pinte. El contexto y la lista
    public BookAdapter(Context context, List<Book> dataList) {
        this.context = context;
        this.bookList = dataList; //lista de libros
        presenter = new DeleteBookPresenter(this);
        favPresenter = new AddFavBookPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    //2. creamos la estructura de cada layout. Vista detalle de cada libro
    // Metodo con el que Android va a inflar, va a crear cada estructura del layout donde irán los datos de cada libro.
    // Vista detalle de cada libro
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false); // el layout book_item para cada libro
        return new BookHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
    }

    //3.metodo para hacer que cada valor de la lista corresponda a los valores y pintarlos en cad elemento del layout
    @Override
    public void onBindViewHolder(BookHolder holder, int position) {

        holder.bookName.setText(bookList.get(position).getName());
        holder.bookYearEdition.setText(bookList.get(position).getYearEditionString());
        holder.bookPageNumber.setText(bookList.get(position).getPagesNumberString());
        holder.bookDescription.setText(bookList.get(position).getDescription());
    }

    //4.metodo para contar el numero de elementos
    @Override
    public int getItemCount() {
        return bookList.size();
    }

    @Override
    public void showError(String errorMessage) {
//        Snackbar.make(snackBarView, errorMessage,
//                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
//        Snackbar.make(snackBarView, message,
//                BaseTransientBottomBar.LENGTH_LONG).show();
    }


    //5.Creamos todos los componentes que tenemos
    public class BookHolder extends RecyclerView.ViewHolder {
        public TextView bookName;
        public TextView bookYearEdition;
        public TextView bookPageNumber;
        public TextView bookDescription;
        public Button modifyBookButton;
        public Button deleteBookButton;
        public Button addFavBookButton;

        public View parentView; //vista padre: recyclerView

        //constructor del holder
        public BookHolder(View view) {
            super(view);
            parentView = view; //Guardamos el componente padre

            bookName = view.findViewById((R.id.book_name));
            bookYearEdition = view.findViewById(R.id.book_yearEdition);
            bookPageNumber = view.findViewById(R.id.book_pageNumber);
            bookDescription = view.findViewById(R.id.book_description);
            modifyBookButton = view.findViewById(R.id.modify_book_button);
            deleteBookButton = view.findViewById(R.id.delete_book_button);
            addFavBookButton = view.findViewById(R.id.add_fav_book_button);

            //pulsando estos botones llamamos al metodo correspondiente
            modifyBookButton.setOnClickListener(v -> modifyBook(getAdapterPosition()));
            deleteBookButton.setOnClickListener(v -> deleteBook(getAdapterPosition()));
            addFavBookButton.setOnClickListener(v -> addFavBook(getAdapterPosition()));
        }


        //metodo boton modificar
        private void modifyBook(int position) {
            Book book = bookList.get(position);

            Intent intent = new Intent(context, ModifyBookView.class);
            intent.putExtra("book", book);
            context.startActivity(intent);
        }

        //metodo boton eliminar libro
        private void deleteBook(int position) {

            //Dialogo para confirmar que se quiere eliminar
            AlertDialog.Builder builder = new AlertDialog.Builder(context); //le pasamos el contexto donde estamos
            builder.setMessage(R.string.are_you_sure_delete_book_message)
                    .setTitle(R.string.delete_book_title)
                    .setPositiveButton("Yes", (dialog, id) -> { //añadir boton de si
                        Book book = bookList.get(position);
                        presenter.deleteBook(book.getId());

                        bookList.remove(position); //borrar de la lista que se muestra
                        notifyItemRemoved(position); //refrescar la lista sin el elemento borrado
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show(); //sin esto no se muestra el dialogo
        }

        private void addFavBook(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Se añadirá a favoritos")
                    .setTitle("Añadir a Favoritos")
                    .setPositiveButton("Yes", (dialog, id) -> {
                        Book book = bookList.get(position);

                        FavBook favBook = new FavBook();
                        favBook.setName(book.getName());
                        favBook.setYearEdition(book.getYearEdition());
                        favBook.setPagesNumber(book.getPagesNumber());
                        favBook.setDescription(book.getDescription());
                        favBook.setEBook(book.isEBook());

                        favPresenter.addFavBook(favBook);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
}
