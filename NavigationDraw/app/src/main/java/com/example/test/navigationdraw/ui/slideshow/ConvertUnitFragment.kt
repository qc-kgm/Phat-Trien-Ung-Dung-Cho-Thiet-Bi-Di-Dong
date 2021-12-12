package com.example.test.navigationdraw.ui.slideshow

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test.navigationdraw.databinding.FragmentConvertunitBinding
import kotlinx.android.synthetic.main.fragment_convertunit.*
import java.lang.NumberFormatException
import kotlin.to

class ConvertUnitFragment : Fragment() {

    val arrUnit = listOf<String>("nm", "\u00B5m", "mm", "cm", "dm", "m", "km")
    var unit1 = ""
    var unit2 = ""
    private lateinit var convertUnitViewModel: ConvertUnitViewModel
    private var _binding: FragmentConvertunitBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        convertUnitViewModel =
            ViewModelProvider(this).get(ConvertUnitViewModel::class.java)

        _binding = FragmentConvertunitBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textSlideshow
//        convertUnitViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val arrayAdapter = ArrayAdapter<String>(view.context, R.layout.simple_spinner_item, arrUnit)
        arrayAdapter.setDropDownViewResource(com.example.test.navigationdraw.R.layout.support_simple_spinner_dropdown_item)
        from.apply {
            adapter = arrayAdapter
            setSelection(0)
        }
        to.apply {
            adapter = arrayAdapter
            setSelection(0)
        }
        unit1 = arrUnit[0]
        unit2 = arrUnit[0]
        val t= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (parent?.id) {
                    from.id -> {
                        unit1 = arrUnit[position]
                        println("unit1 : $unit1")
                    }
                    to.id -> {
                        unit2 = arrUnit[position]
                        println("unit2 : $unit2")
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        from.onItemSelectedListener = t
        to.onItemSelectedListener=t
        reverse.setOnClickListener {
            from.setSelection(arrUnit.indexOf(unit2))
            to.setSelection(arrUnit.indexOf(unit1))
            val t = unit1
            unit1 = unit2
            unit2 = t
        }
        btnConvert.setOnClickListener {
            if (input.text.isNullOrEmpty()) {
                Toast.makeText(view.context, "Nhập giá trị cần chuyển đổi trước", Toast.LENGTH_LONG)
                    .show()
            } else {
                try {
                    val inputs = input.text.toString().toFloat()
                    var res = onConvert(inputs, unit1, unit2)
                    output.text = res.toString()
                } catch (e: NumberFormatException) {
                    Toast.makeText(view.context, "Input phải là số ", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun onConvert(i: Float, unit1: String, unit2: String): Float {
        var hs1 = 0
        var hs2 = 0
        hs1 = when (unit1) {
            "nm" -> -9
            "\u00B5m" -> -6
            "mm" -> -3
            "cm" -> -2
            "dm" -> -1
            "m" -> 0
            "km" -> 3
            else -> 0
        }
        hs2 = when (unit2) {
            "nm" -> 9
            "\u00B5m" -> 6
            "mm" -> 3
            "cm" -> 2
            "dm" -> 1
            "m" -> 0
            "km" -> -3
            else -> 0
        }
        val hs = hs2 + hs1
        return i * Math.pow(10.0, hs.toDouble()).toFloat()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}