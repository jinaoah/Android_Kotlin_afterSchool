package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //url
//        var url = "https://api.github.com/users/${login}"

        //레이아웃 요소 가져오기
        val content = findViewById<TextView>(R.id.content)
        val githubID = findViewById<EditText>(R.id.github_id)
        val searchBtn = findViewById<Button>(R.id.search_btn)

        //Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(
                GsonConverterFactory.create(
//                    GsonBuilder().registerTypeAdapter(
//                        GithubAPIService.GithubUser::class.java,
//                        GithubAPIService.GithubUserDeserializer()
//                    ).create()
                )
            ).build()

        val apiService = retrofit.create(GithubAPIService::class.java)

        val classInfo : Class<GithubAPIService> = GithubAPIService::class.java

        //버튼 클릭 시
        searchBtn.setOnClickListener {
            val id = githubID.text.toString()
            //interface의 메서드 호출
            val apiCallforData = apiService.getUser(id,"token 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")

            apiCallforData.enqueue(object :Callback<GithubAPIService.GithubUser> {
                override fun onResponse(
                    call: Call<GithubAPIService.GithubUser>,
                    response: Response<GithubAPIService.GithubUser>
                ) {
                    val data = response.body()!!
                    Log.d("mytag",data.toString())

                    content.text = "login : ${data.login}\nid: ${data.id}\nname: ${data.name}"
                }

                override fun onFailure(call: Call<GithubAPIService.GithubUser>, t: Throwable) { }
            })
        }
    }
}

