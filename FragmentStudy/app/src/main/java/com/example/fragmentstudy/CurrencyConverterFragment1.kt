package com.example.fragmentstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

//fragment 클래스를 정의하며 fragment 클래스 상속받기
class CurrencyConverterFragment1 : Fragment() {
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

    //화면을 구성할 뷰 객체를 생성하고 반환함과 초기화 작업을 수행하기 위해
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.currency_converter_fragment1,
            container,
            false
        )
        //뷰 객체에서 요소 가져오기 findViewById
        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val result = view.findViewById<TextView>(R.id.result)
        val fromCurrencySpinner = view.findViewById<Spinner>(R.id.from_currency)
        val toCurrencySpinner = view.findViewById<Spinner>(R.id.to_currency)

        //Spinner 초기화
        val currencySelectionArrayAdaper = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_spinner_item,
            listOf("USD","EUR","JPY","KRW")
        )
        currencySelectionArrayAdaper.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        fromCurrencySpinner.adapter = currencySelectionArrayAdaper
        toCurrencySpinner.adapter = currencySelectionArrayAdaper

        //Spinner의 항목을 선택했을 때 메서드..?
        val itemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                result.text = calculateCurrency(
                    amount.text.toString().toDouble(),
                    fromCurrencySpinner.selectedItem.toString(),
                    toCurrencySpinner.selectedItem.toString()
                ).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        fromCurrencySpinner.onItemSelectedListener = itemSelectedListener
        toCurrencySpinner.onItemSelectedListener = itemSelectedListener

        //계산하기 버튼을 눌렀을 때
        calculateBtn.setOnClickListener {
            result.text = calculateCurrency(
                amount.text.toString().toDouble(),
                fromCurrencySpinner.selectedItem.toString(),
                toCurrencySpinner.selectedItem.toString()
            ).toString()
        }

        //amount값이 변경될 때 (
        amount.addTextChangedListener {
            result.text = calculateCurrency(
                amount.text.toString().toDouble(),
                fromCurrencySpinner.selectedItem.toString(),
                toCurrencySpinner.selectedItem.toString()
            ).toString()
        }


        return view
    }
}