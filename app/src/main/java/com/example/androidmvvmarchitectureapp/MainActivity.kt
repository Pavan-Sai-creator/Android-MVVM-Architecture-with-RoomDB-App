package com.example.androidmvvmarchitectureapp

// https://www.youtube.com/watch?v=PU0ua391_u0&list=PLRKyZvuMYSIO0jLgj8g6sADnD0IBaWaw2&index=15

// Note: I didn't have any sqlite database file. So, instead of adding sql file to assets folder in android, I inserted quotes manually by creating
// instance of database and after the db got created with quotes in it, I deleted that code and continued.

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidmvvmarchitectureapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = QuoteDatabase.getDatabase(applicationContext).quoteDAO()
        val quoteRepository = QuoteRepository(dao)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(quoteRepository)
        ).get(MainViewModel::class.java)

        mainViewModel.getQuotes().observe(this, Observer {
            binding.quotes = it.toString()
        })

        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(0,"TestQuote1","TestAuthor1")
            mainViewModel.insertQuote(quote)
        }


    }
}