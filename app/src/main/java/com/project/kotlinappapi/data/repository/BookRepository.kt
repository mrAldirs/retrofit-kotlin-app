package com.project.kotlinappapi.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.project.kotlinappapi.models.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepository {
    val _books = MutableLiveData<List<Book>>()
    val _book = MutableLiveData<Book>()

    fun callbackAll(): Callback<List<Book>> {
        return object : Callback<List<Book>> {
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }

            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        _books.value = it
                    }
                }
            }
        }
    }

    fun callbackSingle(): Callback<Book> {
        return object : Callback<Book> {
            override fun onFailure(call: Call<Book>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }

            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        _book.value = it
                    }
                }
            }
        }
    }
}