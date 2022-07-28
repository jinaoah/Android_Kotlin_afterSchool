package com.example.fragmentstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class CurrencyConverterFragment2 : Fragment() {
    val currencyExchangeMap = mapOf(
        "USD" to 1.0,
        "EUR" to 0.9,
        "JPY" to 110.0,
        "KRW" to 1150.0
    )

    fun calculateCurrency(amount: Double, from: String, to: String) : Double {
        var USDAmount = if(from != "USD"){
            (amount / currencyExchangeMap[from]!!)
        } else {
            amount
        }
        return currencyExchangeMap[to]!! * USDAmount
    }
    lateinit var fromCurrency: String
    lateinit var toCurrency: String

    //화면을 구성할 뷰 객체를 생성하고 반환함과 초기화 작업을 수행하기 위해
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.currency_converter_fragment2,
            container,
            false
        )
        //뷰 객체에서 요소 가져오기 findViewById
        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val result = view.findViewById<TextView>(R.id.result)
        val exchangeType = view.findViewById<TextView>(R.id.exchange_type)

        fromCurrency = arguments?.getString("from","USD")!!
        toCurrency = arguments?.getString("to","USD")!!

        exchangeType.text = "${fromCurrency} => ${toCurrency} 변환"

        calculateBtn.setOnClickListener {
            result.text = calculateCurrency(
                    amount.text.toString().toDouble(),
                    fromCurrency,
                    toCurrency
            ).toString()
        }

        return view
    }

    companion object {
        fun newInstance(from: String, to: String): CurrencyConverterFragment2 {
            val fragment = CurrencyConverterFragment2()

            //번들 객체를 만들고 필요한 데이터 저장
            val args = Bundle() //Bundle() : 여러가지의 타입의 값을 저장하는 Map 클래스
            args.putString("from",from) //키와 값 전달
            args.putString("to",to)
            fragment.arguments = args

            return fragment
        }
    }
}