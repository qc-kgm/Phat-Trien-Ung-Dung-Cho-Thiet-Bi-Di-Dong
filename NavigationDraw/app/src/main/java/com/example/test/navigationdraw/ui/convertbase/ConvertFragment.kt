package com.example.test.navigationdraw.ui.convertbase

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test.navigationdraw.R
import com.example.test.navigationdraw.databinding.FragmentConvertbaseBinding
import kotlinx.android.synthetic.main.fragment_convertbase.*

class ConvertFragment : Fragment() {
    val lsHeso= listOf<Int>(2,8,10,16)
    var heso1=0
    var heso2=0
    private lateinit var convertViewModel: ConvertViewModel
    private var _binding: FragmentConvertbaseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        convertViewModel =
            ViewModelProvider(this).get(ConvertViewModel::class.java)

        _binding = FragmentConvertbaseBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter= ArrayAdapter(view.context,android.R.layout.simple_spinner_item,lsHeso)
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
                Toast.makeText(view.context,"Nhập giá trị cần chuyển đổi trước", Toast.LENGTH_LONG).show()
            }
            else {
                var res=onConvert(input.text.toString().trim(),lsHeso[heso1],lsHeso[heso2])
                output.text=res
            }
        }
    }
    private fun onConvert(x:String, coso1:Int, coso2:Int):String{
        Log.v("T","$x \t ${coso1.toString()} \t ${coso2.toString()}")
//        val t=x.toInt(10)
        val t=x.toInt(coso1).toString(coso2)
//        Log.v("T",t.toString())
//        val t2=t.toString(coso2)
//        Log.v("T",t2)
        return t
    }

}