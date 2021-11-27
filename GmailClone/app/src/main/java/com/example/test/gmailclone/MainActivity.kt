package com.example.test.gmailclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var listEmail : JSONArray
    lateinit var listEmailfake:MutableList<Email>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text=resources.openRawResource(R.raw.mockdata).bufferedReader().use { it.readText() }
        listEmail= JSONArray(text)
        fakeData()
        rclvEmail.adapter=AdapterEmail(listEmailfake)
        rclvEmail.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }
    private fun fakeData() {
        var temp: JSONObject
        listEmailfake= mutableListOf()
        for (i in 0..listEmail.length()-1) {
            temp=listEmail.getJSONObject(i)
            listEmailfake.add(Email(temp.getString("time"),temp.getString("full_name"),temp.getString("email"),
            temp.getString("content"),temp.getString("title")))
        }
    }

}