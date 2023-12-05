package com.project.kotlinappapi.api

import com.project.kotlinappapi.models.Book
import com.project.kotlinappapi.models.User
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("/users")
    fun getUsers(): Call<List<User>>

    @GET("/books")
    fun getBooks(): Call<List<Book>>

    @GET("/books/{id}")
    fun getBooksById(@Path("id") id: String): Call<Book>

    @POST("/books")
    fun postBook(@Body newBook: Book): Call<Book>

    @PUT("/books/{id}")
    fun putBook(@Path("id") id: String, @Body editBook: Book): Call<Book>

    @DELETE("/books/{id}")
    fun deleteBook(@Path("id") id: String): Call<Book>
}