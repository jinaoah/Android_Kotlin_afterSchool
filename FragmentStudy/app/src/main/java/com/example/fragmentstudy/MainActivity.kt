package com.example.fragmentstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity(),
    CurrencyConverterFragment3.CurrencyCalculationListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //beginTransaction 메서드를 호출해 fragment를 추가, 제거, 교체하는 작업에 사용할 FragmentTransaction 객체 반환
        val transaction = supportFragmentManager.beginTransaction()
//      transaction.add(R.id.fragment_container, CurrencyConverterFragment1())

//        transaction.add(R.id.fragment_container,
//            CurrencyConverterFragment2.newInstance("USD","KRW"))
//        transaction.add(R.id.fragment_container,
//            CurrencyConverterFragment2.newInstance("KRW","USD"))
        transaction.add(R.id.fragment_container, CurrencyConverterFragment3())
        transaction.commit()
    }

    override fun onCalculate(result: Double, amount: Double, from: String, to: String) {
        findViewById<TextView>(R.id.result).text = result.toString()
        Log.d("mytag",result.toString())
    }
}