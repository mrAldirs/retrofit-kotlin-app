package com.project.kotlinappapi.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.kotlinappapi.data.adapter.BooksAdapter
import com.project.kotlinappapi.data.viewmodel.BookViewModel
import com.project.kotlinappapi.databinding.ActivityBookBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookBinding
    private lateinit var adapter: BooksAdapter
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = BooksAdapter(ArrayList(), this)
        binding.recyclerView.adapter = adapter

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        binding.btnTambah.setOnClickListener {
            val frag = BookForm()

            frag.show(supportFragmentManager, "form")
        }
    }

    override fun onStart() {
        super.onStart()
        getAll()
    }

    fun getAll() {
        bookViewModel.books.observe(this, Observer { books ->
            adapter.setData(books)
        })

        bookViewModel.getAll()
    }

    fun deleteBook(id: String) {
        AlertDialog.Builder(this)
            .setTitle("Delete Book")
            .setMessage("Are you sure to delete this book?")
            .setPositiveButton("Yes") { dialog, which ->
                bookViewModel.deleteBook(id)
                recreate()
                Toast.makeText(this@BookActivity, "Succes", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}