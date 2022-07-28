package com.example.fragmentstudy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.lang.Exception

class CurrencyConverterFragment3 : Fragment() {
    interface CurrencyCalculationListener { //새로운 인터페이스
        fun onCalculate(result: Double,
                        amount: Double,
                        from: String,
                        to: String
                        )
    }
    lateinit var listener : CurrencyCalculationListener

    override fun onAttach(context: Context){
        super.onAttach(context)

        if(activity is CurrencyCalculationListener) {
            listener = activity as CurrencyCalculationListener
        } else {
            throw Exception("CurrencyCalculationListener 미구현")
        }
    }

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
            R.layout.currency_converter_fragment3,
            container,
            false
        )
        //뷰 객체에서 요소 가져오기 findViewById
        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val exchangeType = view.findViewById<TextView>(R.id.exchange_type)

        fromCurrency = arguments?.getString("from","USD")!!
        toCurrency = arguments?.getString("to","USD")!!

        exchangeType.text = "${fromCurrency} => ${toCurrency} 변환"

        calculateBtn.setOnClickListener {
            val result = calculateCurrency(
                amount.text.toString().toDouble(),
                fromCurrency,
                toCurrency
            )

            //TODO : result값을 액티비티로 전달
            listener.onCalculate(
                result,
                amount.text.toString().toDouble(),
                fromCurrency, toCurrency)
        }

        return view
    }

    companion object {
        fun newInstance(from: String, to: String): CurrencyConverterFragment3 {
            val fragment = CurrencyConverterFragment3()

            //번들 객체를 만들고 필요한 데이터 저장
            val args = Bundle() //Bundle() : 여러가지의 타입의 값을 저장하는 Map 클래스
            args.putString("from",from) //키와 값 전달
            args.putString("to",to)
            fragment.arguments = args

            return fragment
        }
    }
}