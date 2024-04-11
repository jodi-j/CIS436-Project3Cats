package com.example.cis436_project3cats

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.cis436_project3cats.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var nameArray: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //method to interact with API
        fun getCatData() {
            val catUrl = "https://api.thecatapi.com/v1/breeds" +
                    "?api_key=live_yBUe310AABVy5t9rfxI5lB3dJIkU0dAOYIQO7g73eDmn5BpLbXcr2A9ThHfXkBjz"

            val queue = Volley.newRequestQueue(this)

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, catUrl,
                { response ->
                    val catsArray = JSONArray(response)
                    for(i in 0 until catsArray.length()) {
                        val theCat : JSONObject = catsArray.getJSONObject(i)
                        //store cat names
                        nameArray.add(theCat.getString("name"))
                    }//end for

                    // Update spinner after fetching data
                    val spinnerFragment =
                        supportFragmentManager.findFragmentById(R.id.fragmentSpinner) as SpinnerFragment
                    spinnerFragment.setSpinner(nameArray)
                },
                {
                    Log.i("MainActivity", "That didn't work!")
                })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }

        // Call getCatData when the activity starts
        getCatData()
    }

}