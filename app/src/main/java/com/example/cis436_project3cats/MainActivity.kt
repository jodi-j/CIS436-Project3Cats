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
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //method to interact with API
        fun printCatData() {
            val catUrl = "https://api.thecatapi.com/v1/breeds" +
                    "?api_key=live_yBUe310AABVy5t9rfxI5lB3dJIkU0dAOYIQO7g73eDmn5BpLbXcr2A9ThHfXkBjz"

            val queue = Volley.newRequestQueue(this)

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, catUrl,
                { response ->
                    var catsArray : JSONArray = JSONArray(response)
                    //indices from 0 through catsArray.length()-1
                    for(i in 0 until catsArray.length()) {
                        //${} is to interpolate the string /
                        // uses a string template
                        var theCat : JSONObject = catsArray.getJSONObject(i)
                        //now get the properties we want: name and description
                        Log.i("MainActivity",
                            "Cat name: ${theCat.getString("name")}")
                        Log.i("MainActivity",
                            "Cat description: ${theCat.getString("description")}")
                    }//end for
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