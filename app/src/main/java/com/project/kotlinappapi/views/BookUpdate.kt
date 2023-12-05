package com.project.kotlinappapi.views

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.kotlinappapi.data.viewmodel.BookViewModel
import com.project.kotlinappapi.data.viewmodel.UserViewModel
import com.project.kotlinappapi.databinding.FragmentFormBinding
import com.project.kotlinappapi.models.Book
import com.project.kotlinappapi.models.User
import java.util.*

class BookUpdate : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFormBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var bookViewModel: BookViewModel
    private val userId = mutableListOf<String>()
    private var getUserId = ""
    lateinit var adapter: ArrayAdapter<String>
    lateinit var v: View
    val dataList = mutableListOf<String>()
    var date = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(inflater, container, false)
        v = binding.root

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        adapter = ArrayAdapter(v.context, R.layout.simple_list_item_1, dataList)

        datePicker()
        author()
        show()
        getUser()
        submit()

        return v
    }

    private fun datePicker() {
        val today = Calendar.getInstance()
        binding.insDatePublish.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            val month = month + 1
            val msg = "You Selected: $day/$month/$year"
            date = "$year-$month-$day"
        }
    }

    private fun author() {
        userViewModel.users.observe(this, androidx.lifecycle.Observer { users ->
            if (users != null) {
                dataList.add("Pilih Author")

                for (i in users) {
                    dataList.add(i.name)
                    userId.add(i.id)
                }
                binding.insAuthor.adapter = adapter
            }
        })
        userViewModel.getAll()
    }

    private fun getUser() {
        binding.insAuthor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (position > 0) {
                    getUserId = userId[position - 1]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun show() {
        bookViewModel.book.observe(this@BookUpdate, Observer { data ->
            binding.insName.setText(data.name)
            binding.insAuthor.setSelection(adapter.getPosition(data.user.name))
            binding.insDescription.setText(data.description)
        })

        bookViewModel.getBooksById(arguments?.getString("id").toString())
    }

    private fun submit() {
        binding.btnSubmit.setOnClickListener {
            val id = arguments?.getString("id").toString()
            val title = binding.insName.text.toString()
            val author = getUserId
            val datePublish = date
            val description = binding.insDescription.text.toString()

            if (title.isEmpty() || author.isEmpty() || datePublish.isEmpty() || description.isEmpty()) {
                Toast.makeText(this.context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val updateBook = Book(id,title,description,datePublish, User(author,"",""))
                bookViewModel.putBook(id, updateBook)
                Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                (context as BookActivity).getAll()
                dismiss()
            }
        }
    }
}