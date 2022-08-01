package com.example.githubusersearch

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.lang.reflect.Type

interface GithubAPIService {
    //https://api.github.com/users/jinaoah
    @GET("/users/{userID}") //경로
    fun getUser(
        @Path("userID") id: String,
        @Header("Authorization") pat: String
    ): Call<GithubUser>

    data class GithubUser(val id: Int,
                          val login: String,
                          val name: String?,
                          val followers: Int,
                          val following: Int)

//    class GithubUserDeserializer : JsonDeserializer<GithubUser> {
//        override fun deserialize(
//            json: JsonElement?,
//            typeOfT: Type?,
//            context: JsonDeserializationContext?
//        ): GithubUser {
//            val root = json?.asJsonObject
//            val login = root?.getAsJsonPrimitive("login")?.asString
//            val id = root?.getAsJsonPrimitive("id")?.asInt
//
//            return GithubUser(id!!, login!!)
//        }
//    }
}