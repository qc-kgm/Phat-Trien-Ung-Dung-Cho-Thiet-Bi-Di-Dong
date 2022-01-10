package com.example.test.tuan16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.tuan16.databinding.ActivityReadFileBinding
import java.io.*

class ReadFileActivity : AppCompatActivity() {
    lateinit var root:String
    private lateinit var binding:ActivityReadFileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadFileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(intent.getStringExtra("rootpath") != null){
            root= intent.getStringExtra("rootpath")!!
        }
        binding.tv.text= readFile()
    }
    fun readFile():String{
        val f = File(root)
        if (root.substringAfterLast(".",root) == "txt") {
            val fis = FileInputStream(f)
            val reader = BufferedReader(InputStreamReader(fis))
//        while (val str = reader.readText())
            val str = reader.readText()
            fis.close()
            return str
        }
        return "Cannot open this file"
    }
}