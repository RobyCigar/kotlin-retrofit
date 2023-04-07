package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val btn = binding.submitBtn
//        val res = binding.result
//        btn.setOnClickListener {
//            val name = binding.elName.text.toString()
//            Toast.makeText(this, name, Toast.LENGTH_LONG).show()
//            res.text = name
//        }
//        val incrementButton = findViewById<Button>(R.id.increment_button)
//        val counterTextView = findViewById<TextView>(R.id.counter_text_view)
//        var count = 0
//        incrementButton.setOnClickListener {
//            count++
//            counterTextView.text = count.toString()
//        }
    }

    override fun onStart() {
        super.onStart()
        getDataFromApi()
    }

    private fun getDataFromApi() {
        ApiService.endpoint.getPhotos()
            .enqueue(object: Callback<List<MainModel>> {
                override fun onResponse(
                    call: Call<List<MainModel>>,
                    response: Response<List<MainModel>>
                ) {
                    if(response.isSuccessful) {
                        response.body()?.let { showPhotos(it) }
                    }
                }

                override fun onFailure(call: Call<List<MainModel>>, t: Throwable) {
                    printLog(t.toString())
                }

            })
    }

    fun printLog(msg: String) {
        Log.d("HELLO", msg)
    }

    fun showPhotos(photos: List<MainModel>) {
        for ((index, photo) in photos.withIndex()) {
            Log.d("Index ke - $index", photo.url)
        }
    }

}