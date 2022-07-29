package com.example.anrstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.sqrt
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val result = findViewById<TextView>(R.id.result)

        //ANR 버튼 클릭 시
        findViewById<Button>(R.id.btn).setOnClickListener {
            Toast.makeText( //Toast : 사용자에게 짧은 메시지 형식으로 정보를 전달하는 팝업
                this,"Clicked!",
                Toast.LENGTH_SHORT).show()
        }
        //CLick Me 버튼 클릭 시
        findViewById<Button>(R.id.anr).setOnClickListener {
            Thread(Runnable {
                var sum = 0.0
                for (i in 1..60) {
                    sum += sqrt(Random.nextDouble())
                    Thread.sleep(100)
                }
                Log.d("mytag",sum.toString())
                runOnUiThread {
                    result.text = sum.toString()
                }
            }).start()
        }
    }
}