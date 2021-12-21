package com.example.test.exerciseweek13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val DOMAIN_API = "https://lebavui.github.io"
    }
     var listUser : MutableList<UserInfo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rclvUser.adapter=AdapterUser(listUser)
        rclvUser.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        getJsonFromAPI(DOMAIN_API+"/jsons/users.json")
    }
    fun getJsonFromAPI(link : String){

        val requestq = Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET , link , null ,{ response ->
                Log.v("TEST","get data from api OK")
                run {
                    for ( i in 0..response.length()-1){
                        val o = response.getJSONObject(i)
                        listUser.add(UserInfo(
                            o.getInt("id"),
                            o.getString("name"),
                            o.getString("username"),
                            o.getString("email"),
                            o.getJSONObject("avatar").getString("thumbnail"),
                            o.getJSONObject("avatar").getString("photo"),
                            o.getJSONObject("address").getString("street")+
                                   " - "+ o.getJSONObject("address").getString("suite")+
                                    " - " + o.getJSONObject("address").getString("city"),
                            o.getJSONObject("address").getString("zipcode"),
                            o.getString("phone"),
                            o.getJSONObject("company").getString("name")+"\n"+
                                    o.getJSONObject("company").getString("catchPhrase")
                        ))
                    }
//                    Log.v("TEST",gson.fromJson(response.getJSONObject(1),UserInfo::class))
                }
                rclvUser.adapter?.notifyDataSetChanged()
            } ,
            { error ->
                Log.v("TEST",error.message.toString())
            }
        )
        requestq.add(jsonArrayRequest)
    }

}