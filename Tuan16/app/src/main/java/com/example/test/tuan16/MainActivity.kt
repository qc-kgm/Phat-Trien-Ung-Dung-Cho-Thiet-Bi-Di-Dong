package com.example.test.tuan16

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.EnvironmentCompat
import com.example.test.tuan16.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    companion object {
         val REQUEST_CODE_PERMISSION = 1000
        fun getList(rootPath: String): Array<String> {
            val f = File(rootPath)
            return f.list()
        }
        fun showListFile( array : Array<String>,context: Context): ArrayAdapter<String> {
            return ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,array)
        }
        fun navigate(rootPath:String , context: Context){
            val f = File(rootPath)

            if(f.isDirectory){
                val intent = Intent(context,ViewFolderActivity::class.java)
                intent.putExtra("rootpath",rootPath)
                context.startActivity(intent)
            } else {
                val intent = Intent(context,ReadFileActivity::class.java)
                intent.putExtra("rootpath",rootPath)
                startActivity(context,intent,null)
            }
        }
    }
    lateinit var root : File
    private lateinit var binding : ActivityMainBinding
//    val rootpath = lazy { Environment.getStorageDirectory().absolutePath }
//    lateinit var binding : ActivityMainBi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermission()
        root = Environment.getExternalStorageDirectory()
//    Log.v("TEST",getExternalFilesDir(null)!!.absolutePath)
        Log.v("TEST",root.absolutePath)
        val array = getList(root.absolutePath)
        array.forEach {
            Log.v("TEST",it.toString())
        }

        binding.listView.adapter = showListFile(array , this)
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            navigate(root.absolutePath+"/"+array[position],this)
        }
    }
    fun checkPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                requestPermissions(arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION)
            }
        }

    }


}