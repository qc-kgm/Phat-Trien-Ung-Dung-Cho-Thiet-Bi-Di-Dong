package com.example.test.convert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val lsHeso= listOf<Int>(2,8,10,16)
    var heso1=0
    var heso2=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,lsHeso)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        from.adapter=adapter
        to.adapter=adapter

        from.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                heso1=position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")

            }

        }
        to.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                heso2=position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")

            }

        }


        reverse.setOnClickListener {
            val t =heso1
            from.setSelection(heso2)
            to.setSelection(heso1)
            heso1=heso2
            heso2=t
        }

        btnConvert.setOnClickListener {
            if(input.text.isNullOrEmpty()){
                Toast.makeText(this,"Nhập giá trị cần chuyển đổi trước",Toast.LENGTH_LONG).show()
            }
            else {
                var res=onConvert(input.text.toString().trim(),lsHeso[heso1],lsHeso[heso2])
                output.text=res
            }
        }
    }
    private fun onConvert(x:String, coso1:Int, coso2:Int):String{
        val t=x.toInt(10)
        val t2=t.toString(coso2)
        return t2
    }
}