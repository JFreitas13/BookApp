package com.svalero.bookApp.api;

import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.domain.Bookstore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookApiInterface {

    //books
    @GET("/books")
    Call<List<Book>> getBooks();

    @POST("/books")
    Call<Book> addBook(@Body Book book);

    @DELETE("/books/{id}")
    Call<Void> deleteBooks(@Path("id") long id);

    @PUT("/books/{id}")
    Call<Book> modifyBook(@Path("id") long id, @Body Book book);

    //bookstores
    @GET("/bookstores")
    Call<List<Bookstore>> getBookstores();

    @POST("/bookstores")
    Call<Bookstore> addBookstore(@Body Bookstore bookstore);

    @DELETE("/bookstores/{id}")
    Call<Void> deleteBookstores(@Path("id") long id);

    @PUT("/bookstores/{id}")
    Call<Bookstore> modifyBookstore(@Path("id") long id, @Body Bookstore bookstore);


}
