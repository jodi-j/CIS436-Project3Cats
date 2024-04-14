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

class MainActivity : AppCompatActivity(), SpinnerFragment.SpinnerListener {
    private lateinit var binding : ActivityMainBinding
    private var nameArray: MutableList<String> = ArrayList()
    //create list of catInfo (list of class objects)
    private var catInfoList: MutableList<CatInfo> = ArrayList()
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
                        //store cat info in class
                        val catInfo = CatInfo(
                            name = theCat.getString("name"),
                            temperament = theCat.getString("temperament"),
                            origin = theCat.getString("origin"),
                            description = theCat.getString("description"),
                            //not all cats have images for some reason so use elvis operator to prevent app crash
                            //also have default image for those that api doesnt provide
                            imageURL = theCat.optJSONObject("image")?.getString("url") ?: "default_image_url"
                        )
                        catInfoList.add(catInfo)

                        //store cat names
                        //nameArray.add(theCat.getString("name"))
                    }//end for

                    // Update spinner after fetching data
                    val spinnerFragment =
                        supportFragmentManager.findFragmentById(R.id.fragmentSpinner) as SpinnerFragment
                    //uses catInfoList to crate new list of all cat names
                    spinnerFragment.setSpinner(catInfoList.map { it.name }.toMutableList(), this)
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

    override fun onItemSelect(catChoice: String) {
        //get catinfo fragment
        val catInfoFragment = supportFragmentManager.findFragmentById(R.id.fragmentCatInfo) as CatInfoFragment

        // Find the CatInfo object for the selected cat
        val selectedCatInfo = catInfoList.find { it.name == catChoice }
        selectedCatInfo?.let {
            catInfoFragment.updateTextDesc(it)
        }

    }


}