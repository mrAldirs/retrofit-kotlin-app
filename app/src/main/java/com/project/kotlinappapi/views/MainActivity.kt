package com.project.kotlinappapi.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.kotlinappapi.data.adapter.UsersAdapter
import com.project.kotlinappapi.data.viewmodel.BookViewModel
import com.project.kotlinappapi.data.viewmodel.UserViewModel
import com.project.kotlinappapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter(ArrayList())
        binding.recyclerView.adapter = adapter

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.users.observe(this, Observer { users ->
            adapter.setData(users)
        })

        userViewModel.getAll()

//        ApiClient.getService()
//            .getUsers()
//            .enqueue(object : Callback<List<User>> {
//                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    Log.d("Error", t.message.toString())
//                }
//
//                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    if (response.isSuccessful) {
//                        val data = response.body()
//                        Log.d("Response", data.toString())
//                        data?.let {
//                            adapter.setData(it)
//                        }
//                    }
//                }
//            })
    }
}