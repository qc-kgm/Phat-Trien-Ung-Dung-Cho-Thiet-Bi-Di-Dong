package com.example.test.navigationdraw.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test.navigationdraw.databinding.FragmentCalculatorBinding
import kotlinx.android.synthetic.main.fragment_calculator.*
import java.lang.Exception

class CalculatorFragment : Fragment() {
    var so1=0
    var so2:Int=0
    var toantu=""
    var res=0
    var state=false
    var isError=false
    private lateinit var lsSo:MutableList<Button>
    private lateinit var calculatorViewModel: CalculatorViewModel
    private var _binding: FragmentCalculatorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calculatorViewModel =
            ViewModelProvider(this).get(CalculatorViewModel::class.java)

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // copy from app calculator

        lsSo= mutableListOf(btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9)

        for (i in lsSo){
            i.setOnClickListener {
                clickphimso(i)
//                updateNowExp(now_exp)
            }
        }
        btnPlus.setOnClickListener {
            if(state){
                try {
                    so2 = Integer.parseInt(tvResult.text.toString())
                    res = caculate(so1, so2, toantu)
                    state = false
                    toantu = "+"
                    tvPreRes.text = res.toString() + toantu
                    tvResult.text = res.toString()
                    so1 = res
                } catch (e: Exception){
                    isError=true
                    tvPreRes.text=so1.toString()+toantu
                    tvResult.text="Cannot divice by zero"
                    so1=0
                    so2=0
                }
            }
            else {
                toantu ="+"
                so1 = Integer.parseInt(tvResult.text.toString())
                tvPreRes.text=so1.toString()+toantu
            }
        }
        btnMinus.setOnClickListener {
            if(state){
                try {
                    so2 = Integer.parseInt(tvResult.text.toString())
                    res = caculate(so1, so2, toantu)
                    state = false
                    toantu = "-"
                    tvPreRes.text = res.toString() + toantu
                    tvResult.text = res.toString()
                    so1 = res
                } catch (e: Exception){
                    isError=true
                    tvPreRes.text=so1.toString()+toantu
                    tvResult.text="Cannot divice by zero"
                    so1=0
                    so2=0
                }
            }
            else {
                toantu ="-"
                so1 = Integer.parseInt(tvResult.text.toString())
                tvPreRes.text=so1.toString()+toantu
            }

        }
        btnMulti.setOnClickListener {
            if(state){
                try {
                    so2 = Integer.parseInt(tvResult.text.toString())
                    res = caculate(so1, so2, toantu)
                    state = false
                    toantu = "*"
                    tvPreRes.text = res.toString() + toantu
                    tvResult.text = res.toString()
                    so1 = res
                } catch (e: Exception){
                    isError=true
                    tvPreRes.text=so1.toString()+toantu
                    tvResult.text="Cannot divice by zero"
                    so1=0
                    so2=0
                }
            }
            else {
                toantu ="*"
                so1 = Integer.parseInt(tvResult.text.toString())
                tvPreRes.text=so1.toString()+toantu
            }
        }
        btnDiv.setOnClickListener {
            if(state){
                so2=Integer.parseInt(tvResult.text.toString())
                try {
                    res = caculate(so1, so2, toantu)
                    state = false
                    toantu = "/"
                    tvPreRes.text = res.toString() + toantu
                    tvResult.text = res.toString()
                    so1=res
                } catch (e: Exception){
                    isError=true
                    tvPreRes.text=so1.toString()+toantu
                    tvResult.text="Cannot divice by zero"
                    so1=0
                    so2=0
                }
            }
            else {
                toantu ="/"
                so1 = Integer.parseInt(tvResult.text.toString())
                tvPreRes.text=so1.toString()+toantu
            }
        }
        btnDoiDau.setOnClickListener {

        }
        btnDel.setOnClickListener {
            val temp=tvResult.text.toString()
            if (temp.length!=1)
                tvResult.text=temp.removeRange(temp.length-2,temp.length-1)
            else  tvResult.text="0"
        }
        btnCaculate.setOnClickListener {
            if(toantu=="") {
                res=Integer.parseInt(tvResult.text.toString())
                tvPreRes.text=tvResult.text.toString()+"="
            }
            else {
                so2=Integer.parseInt(tvResult.text.toString())
                try {
                    res = caculate(so1, so2, toantu)
                    tvResult.text=res.toString()
                    tvPreRes.text=so1.toString()+toantu+so2.toString()+"="
                }
                catch (e : Exception){
                    tvResult.text="Cannot divice by zero"
                    isError=true
                    tvPreRes.text=so1.toString()+toantu
                    so1=0
                    so2=0
                }
                toantu=""
                state=false
            }
        }
        btnC.setOnClickListener {
            tvResult.text="0"
            so1=0
            so2=0
            tvPreRes.text=""
        }
        btnCE.setOnClickListener {
            if(toantu==""){
                tvResult.text="0"
                so1=0
                so2=0
                tvPreRes.text=""
            }
            else {
                tvResult.text="0"
            }
        }

    }
    fun caculate(a:Int,b:Int,c:String) :Int{
        return when(c){
            "+" ->{
                a+b
            }
            "-"->{
                a-b
            }
            "*" ->{
                a*b
            }
            "/" -> {
                if (b != 0) {
                    a / b
                }
                else throw Exception()
            }
            else -> {
                0
            }
        }
    }
    //    fun updateNowExp(s:String){
//        tvResult.text=s
//    }
    fun clickphimso(v: View) {
        if (isError) {
            tvPreRes.text=""
            tvResult.text=""
            isError=false
            toantu=""
            state=false
        }
        if(toantu!="" && state==false) {
            tvResult.text=""
            state=true
        }
        if(tvPreRes.text.contains('=')) {
            tvResult.text=""
            so1=0
            so2=0
        }
        if(tvResult.text=="0") tvResult.text=""

        when (v.id) {
            btn0.id -> {
                tvResult.text=tvResult.text.toString()+"0"
            }
            btn1.id -> {
//                Toast.makeText(activity,"sadasd", Toast.LENGTH_LONG).show()
                tvResult.text=tvResult.text.toString()+"1"
//                Log.v("TAG", tvResult.text)
            }
            btn2.id -> {
                tvResult.text=tvResult.text.toString()+"2"
            }
            btn3.id -> {
                tvResult.text=tvResult.text.toString()+"3"
            }
            btn4.id -> {
                tvResult.text=tvResult.text.toString()+"4"
            }
            btn5.id -> {
                tvResult.text=tvResult.text.toString()+"5"
            }
            btn6.id -> {
                tvResult.text=tvResult.text.toString()+"6"
            }
            btn7.id -> {
                tvResult.text=tvResult.text.toString()+"7"
            }
            btn8.id -> {
                tvResult.text=tvResult.text.toString()+"8"
            }
            btn9.id -> {
                tvResult.text=tvResult.text.toString()+"9"
            }
        }

    }
}