package com.example.coba2.data

import android.app.Application
import com.android.volley.BuildConfig
import com.example.coba2.model.Barang
import com.example.coba2.model.BarangResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class ApiMain : Application() {
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.88.128/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        val services: ApiServices = retrofit.create(ApiServices::class.java)
        val serviceget2: ServicePostBarang = retrofit.create(ServicePostBarang::class.java)
    val serviceTest : TestApiServices = retrofit.create(TestApiServices::class.java)
}

interface TestApiServices {
    @GET("temp/test.php")
    fun getApiServices(@Query("key") key:String,
                       @Query("content") content: String)
    : Call<String>
}

interface ApiServices {
    @GET("temp/getbarang.php")
    fun getAllBarang(): Call<BarangResponse>
}
interface  ServicePostBarang{
    @POST("temp/getbarang.php")
    fun getAllBarang2(): Call<List<Barang>>
}