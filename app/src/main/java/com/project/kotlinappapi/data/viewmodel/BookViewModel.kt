package com.project.kotlinappapi.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.kotlinappapi.api.ApiClient
import com.project.kotlinappapi.data.repository.BookRepository
import com.project.kotlinappapi.models.Book

class BookViewModel : ViewModel() {
    private val repository = BookRepository()
    val books: MutableLiveData<List<Book>> get() = repository._books
    val book: MutableLiveData<Book> get() = repository._book

    fun getAll() {
        ApiClient.getService()
            .getBooks()
            .enqueue(repository.callbackAll())
    }

    fun postBook(newBook: Book) {
        ApiClient.getService()
            .postBook(newBook)
            .enqueue(repository.callbackSingle())
    }

    fun putBook(id: String, editBook: Book) {
        ApiClient.getService()
            .putBook(id, editBook)
            .enqueue(repository.callbackSingle())
    }

    fun getBooksById(id: String) {
        ApiClient.getService()
            .getBooksById(id)
            .enqueue(repository.callbackSingle())
    }

    fun deleteBook(id: String) {
        ApiClient.getService()
            .deleteBook(id)
            .enqueue(repository.callbackSingle())
    }
}