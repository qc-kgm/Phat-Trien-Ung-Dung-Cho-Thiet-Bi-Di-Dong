package com.example.test.tuan16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.test.tuan16.databinding.ActivityViewFolderBinding

class ViewFolderActivity : AppCompatActivity() {
    lateinit var root: String
    private lateinit var binding: ActivityViewFolderBinding
    lateinit var array:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewFolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.getStringExtra("rootpath") != null) {
            root = intent.getStringExtra("rootpath")!!
        }
        showListView()
        binding.lv.setOnItemClickListener { parent, view, position, id ->
            MainActivity.navigate(root+"/"+array[position],this)
        }
    }

    fun showListView() {
        array = MainActivity.getList(root)
        binding.lv.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array)
    }
}