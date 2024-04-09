package com.example.cis436_project3cats

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.cis436_project3cats.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //method to interact with API
        fun printCatData() {
            var catUrl = "https://api.thecatapi.com/v1/breeds" +
                    "?api_key=USE_YOUR_OWN_KEY_YOU_NAUGHTY_THIEF"

            val queue = Volley.newRequestQueue(this)

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, catUrl,
                { response ->
                    Log.i("MainActivity", response.toString())
                },
                {
                    Log.i("MainActivity", "That didn't work!")
                })
                // Add the request to the RequestQueue.
                queue.add(stringRequest)

        }

        //set button handler
        binding.btnGetCatData.setOnClickListener {
            printCatData()
        }

    }
}