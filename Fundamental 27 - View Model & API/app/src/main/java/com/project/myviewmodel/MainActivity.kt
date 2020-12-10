package com.project.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception
import java.text.DecimalFormat

// TODO 5: lengkapi untuk recycler view nya
class MainActivity : AppCompatActivity() {
    private lateinit var adapter: WeatherAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = WeatherAdapter()
        adapter.notifyDataSetChanged()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // setWeather diganti jadi gini
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        btnCity.setOnClickListener {
            val city = editCity.text.toString()
            if (city.isEmpty()) return@setOnClickListener
            showLoading(true)
            //setWeather(city)

            mainViewModel.setWeather(city)
        }

        mainViewModel.getWeathers().observe(this, Observer { weatherItems ->
            if (weatherItems != null){
                adapter.setData(weatherItems)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean){
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    /*fun setWeather(cities: String){
        val listItems = ArrayList<WeatherItems>()

        val apiKey = "0ae0c45389be3856855bc191e72a29c6"
        val url = "https://api.openweathermap.org/data/2.5/group?id=$cities&units=metric&appid=${apiKey}"

        val client = AsyncHttpClient()

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("list")

                    for (i in 0 until list.length()){
                        val weather = list.getJSONObject(i)
                        val weatherItems = WeatherItems()
                        weatherItems.id = weather.getInt("id")
                        weatherItems.name = weather.getString("name")
                        weatherItems.currentWeather = weather.getJSONArray("weather").getJSONObject(0).getString("main")
                        weatherItems.description = weather.getJSONArray("weather").getJSONObject(0).getString("description")
                        val tempInKelvin = weather.getJSONObject("main").getDouble("temp")
                        val tempInCelsius = tempInKelvin - 273
                        weatherItems.temperature = DecimalFormat("##.##").format(tempInCelsius)
                        listItems.add(weatherItems)
                    }

                    adapter.setData(listItems)
                    showLoading(false)

                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }

        })
    }*/
}