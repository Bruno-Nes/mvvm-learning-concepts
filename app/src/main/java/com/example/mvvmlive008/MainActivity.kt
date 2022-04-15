package com.example.mvvmlive008

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlive008.adapters.MainAdapter
import com.example.mvvmlive008.databinding.ActivityMainBinding
import com.example.mvvmlive008.repositories.MainRepository
import com.example.mvvmlive008.rest.RetrofitService
import com.example.mvvmlive008.viewmodel.main.MainViewModel
import com.example.mvvmlive008.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel : MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter{
        openLink(it.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer{
            Log.i(TAG, "onStart: $it")
            adapter.setLiveList(it)
        })
        viewModel.errorMassage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllLives()
    }

    private fun openLink(link : String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}