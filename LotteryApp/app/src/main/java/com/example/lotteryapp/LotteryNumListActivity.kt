package com.example.lotteryapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LotteryNumListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottery_num_list)

        val pref = getSharedPreferences("nums", Context.MODE_PRIVATE)

        var lottoNums: String? = pref.getString("lottonums", "")
        var numList = if(lottoNums == ""){
            mutableListOf<String>()
        }else {
            lottoNums!!.split(",").toMutableList()
        }

        val listView = findViewById<RecyclerView>(R.id.num_list)
        listView.setHasFixedSize(true)
        //3. 레이아웃 매니저 : 리스트에 포함될 각 항목이 표시될 방식을 관리
        listView.layoutManager = LinearLayoutManager(this)

        //4,5 item 생성, adapter
        listView.adapter = LotteryListAdapter(numList)
        listView.setHasFixedSize(true)
    }
}