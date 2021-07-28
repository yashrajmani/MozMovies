package com.yash.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MovieItemClicked {
    private lateinit var mAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = MovieListAdapter(this)
        recyclerView.adapter = mAdapter

        Toast.makeText(this,"Refreshed Latest Movies ! ",Toast.LENGTH_LONG).show()



    }

    private fun fetchData() {

        val url= "https://api.themoviedb.org/3/movie/popular?api_key=50f9b3220018a9a5b989f35bfe2cd28a&language=en-US&page=1"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                val movieJsonArray=it.getJSONArray("results")
                val movieArray=ArrayList<Movie>()
                for (i in 0 until movieJsonArray.length())
                {
                    val movieJsonObject=movieJsonArray.getJSONObject(i)
                    val movie = Movie(
                        movieJsonObject.getString("title"),

                        movieJsonObject.getString("release_date"),

                        movieJsonObject.getString("overview")
                    )
                    movieArray.add(movie)
                }
                mAdapter.updateMovie(movieArray)

            },
            Response.ErrorListener {


            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)



    }

    override fun onItemClicked(item: Movie)
    {
//        Toast.makeText(this,"clicked this $item",Toast.LENGTH_SHORT).show()
    }



}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/// JSON

//    fun loading()
//    {
//        val queue = Volley.newRequestQueue(this)
//        val url = "https://api.themoviedb.org/3/movie/top_rated?api_key=50f9b3220018a9a5b989f35bfe2cd28a&language=en-US&page=1"
//
//
//        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
//            Response.Listener { response ->
//                val pos=response.getString("poster_path")
//                Glide.with(this).load(pos).into(image)
//
//            },
//            Response.ErrorListener {
//            })
//
//// Access the RequestQueue through your singleton class.
//        queue.add(jsonObjectRequest)
//    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////