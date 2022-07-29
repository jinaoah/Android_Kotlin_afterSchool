package com.example.lotteryapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    //전역변수 선언 부분
    //lateinit : 나중에 초기화를 할 수 있게 미뤄주는 키워드 , var만 사용 가능
    lateinit var currentNums: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //3. SharedPreferences 가져오기
        val pref = getSharedPreferences("nums", Context.MODE_PRIVATE)

        val lottoNumView = findViewById<TextView>(R.id.lotto_text)
        currentNums = generateRandomLottoNum(6, "-")
        lottoNumView.text = currentNums

        //온클릭 리스너 : 클릭 시 번호 생성
        val generateNumBtn = findViewById<Button>(R.id.gen_num)
        generateNumBtn.setOnClickListener {
            currentNums = generateRandomLottoNum(6,"-")
            lottoNumView.text = currentNums
        }

        //3. SharedPreferneces에 로또 번호 저장 lottoText.text
        val saveNumBtn = findViewById<Button>(R.id.save_num)
        saveNumBtn.setOnClickListener {
            var lottoNums: String? = pref.getString("lottonums", "")
            var numList = if(lottoNums == ""){
                mutableListOf<String>()
            }else {
                lottoNums!!.split(",").toMutableList()
            }
            numList.add(currentNums)
            Log.d("my_tag", numList.size.toString())
            Log.d("my_tag", numList.toString())

            val editor = pref.edit()
            editor.putString("lottonums",numList.joinToString("-"))
            editor.apply() //변동사항 저장
        }
        findViewById<Button>(R.id.num_list).setOnClickListener {
            val intent = Intent(this,LotteryNumListActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.win_num).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.dhlottery.co.kr/gameResult.do?method=byWin&wiselog=M_A_1_8"))
            startActivity(intent)
        }
    }

    //2. 숫자 생성 함수!!!!!!!!!!!!!!
    //2-2 메서드 (숫자 인자 전달) 리턴값 : String
    fun generateRandomLottoNum(n:Int, sep:String): String {
        var randNumList = mutableListOf<Int>()
        for (i in 1..n){
            randNumList.add((1..45).random())
        }
        return randNumList.joinToString(sep)
    }
}
//1.
//            //랜덤숫자를 담을 수정 가능한 리스트
//            var randList = mutableListOf<Int>();
//
//            //반복문
//            for (i in 0..5){
//                randList.add((1..45).random())
//            }
//            val lottoNum = randList.joinToString(" ")
//            Log.d("mytag",lottoNum.toString())