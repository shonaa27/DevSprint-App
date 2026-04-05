package com.example.coffeemood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quote = findViewById<TextView>(R.id.kanyeQuote)
        val advice = findViewById<TextView>(R.id.advice)
        val dogImage = findViewById<ImageView>(R.id.dogImage)
        val avatar = findViewById<ImageView>(R.id.avatar)
        val btn = findViewById<Button>(R.id.refreshBtn)

        fun loadData() {

            // Kanye Quote
            val request1 = Request.Builder().url("https://api.kanye.rest").build()
            client.newCall(request1).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}

                override fun onResponse(call: Call, response: Response) {
                    val json = JSONObject(response.body!!.string())
                    val text = json.getString("quote")
                    runOnUiThread {
                        quote.text = "☕ \"$text\""
                    }
                }
            })

            // Advice
            val request2 = Request.Builder().url("https://api.adviceslip.com/advice").build()
            client.newCall(request2).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}

                override fun onResponse(call: Call, response: Response) {
                    val json = JSONObject(response.body!!.string())
                    val adviceText = json.getJSONObject("slip").getString("advice")
                    runOnUiThread {
                        advice.text = "💬 $adviceText"
                    }
                }
            })

            // Dog Image
            val request3 = Request.Builder().url("https://dog.ceo/api/breeds/image/random").build()
            client.newCall(request3).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}

                override fun onResponse(call: Call, response: Response) {
                    val json = JSONObject(response.body!!.string())
                    val img = json.getString("message")
                    runOnUiThread {
                        Glide.with(this@MainActivity).load(img).into(dogImage)
                    }
                }
            })

            // Avatar
            val avatarUrl = "https://api.dicebear.com/7.x/pixel-art/png?seed=coffee"
            Glide.with(this).load(avatarUrl).into(avatar)
        }

        loadData()

        btn.setOnClickListener {
            loadData()
        }
    }
}