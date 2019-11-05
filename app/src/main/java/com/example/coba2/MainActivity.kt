package com.example.coba2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.util.Log
import android.widget.TextView
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.example.coba2.adapter.CustomAdapter
import com.example.coba2.data.ApiMain
import com.example.coba2.model.Barang
import com.example.coba2.model.BarangResponse
import com.example.coba2.model.ListBarang
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import java.lang.reflect.Type
import java.net.URL


class MainActivity : AppCompatActivity(){
    lateinit var context: Context
    var txtView1: TextView? =null
    var listBarang : List<Barang>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context =applicationContext

        //val search = findViewById<EditText>(R.id.editText)
        //val btnShow = findViewById<Button>(R.id.btnSearch)
        txtView1 = findViewById<TextView>(R.id.textView1)
        txtView1?.text = "Hello"
        var header = HashMap<String, String>();






//        quee.add(myReq)
        /*
        val postServices = DataRepository.create("http://192.168.88.128/temp/getbarang.php/")
        postServices.getPosts().enqueue(object : Callback<List<Barang>>{
            override fun onResponse(
                call: Call<List<Barang>>,
                response: retrofit2.Response<List<Barang>>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    textView1.text =response.body().toString()

                    if (data != null) {
                        listBarang=data
                        var adapter = CustomAdapter(applicationContext, R.layout.custom_list,
                            data!!
                        )
                        listBarangLayout.adapter = adapter
                    }
                    Log.d("tag", "Responsenya ${data?.size}")
                    data?.map {
                        Log.d("tag", "datanya ${it.nama_barang}")
                    }
                }
            }

            override fun onFailure(call: Call<List<Barang>>, t: Throwable) {
                Log.e("tag", "errornya ${t.message}")
            }
        })*/

        // ======== RECOMENEDD =========
        // to Get json array without name parameter
        ApiMain().serviceget2.getAllBarang2().enqueue(object : Callback<List<Barang>>{
            override fun onFailure(call: Call<List<Barang>>, t: Throwable) {
                textView1?.text = t.message
            }

            override fun onResponse(
                call: Call<List<Barang>>,
                response: retrofit2.Response<List<Barang>>
            ) {
                response.body()?.let {
                    //textView1?.text = it.size.toString()
                    val adapter = CustomAdapter(context, R.layout.custom_list, it)
                    listBarangLayout.adapter = adapter
                }//To change body of created functions use File | Settings | File Templates.
            }
        })
        // to get json array with parameter name
        /*
        ApiMain().services.getAllBarang().enqueue(object : Callback<BarangResponse>{
            override fun onFailure(call: Call<BarangResponse>, t: Throwable) {
                textView1?.text = t.message //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<BarangResponse>,
                response: retrofit2.Response<BarangResponse>
            ) {
                if(response.code()==200)
                {
                    response.body()?.teams?.let {
                        textView1?.text = it.size.toString()
                        val adapter = CustomAdapter(context, R.layout.custom_list, it)
                        listBarangLayout.adapter = adapter
                    }
                }
            }

        })*/
        // ====== RECOMENDED ===========

        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.88.128/temp/test.php?test=12314&content=apa akusalah"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView1?.text = response
            },
            Response.ErrorListener { textView1?.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }

    private fun createMyRequestSuccessListener() : Response.Listener<String> {
        return Response.Listener<String> { response ->
            if(response!=null)
                txtView1?.text = response
        }
    }

    private fun createMyRequestErrorListener(): Response.ErrorListener {
        return Response.ErrorListener { error ->
            var toast = Toast.makeText(this, error.message, Toast.LENGTH_LONG)
            toast.show()
            txtView1?.text=error.message
            // Do whatever you want to do with error.getMessage();
        }
    }

    fun fillList(params: HashMap<String, String>)
    {
        var httpCall: HttpCall = HttpCall();
        httpCall.methodType = HttpCall.GET()
        httpCall.VURL = "http://192.168.88.156/temp/getbarang.php"
        httpCall.params = params
        HttpRequest().execute(httpCall)
    }

    private fun createMyReqSuccessListener(): Response.Listener<ArrayList<ListBarang>> {
        return Response.Listener { response ->
            if(response!=null)
            {
                var list : ArrayList<ListBarang> = response
                txtView1?.text = list[0].kode_barang
                var toast = Toast.makeText(this, response.toString(), Toast.LENGTH_LONG)
                toast.show()
            }
            // Do whatever you want to do with response;
            // Like response.tags.getListing_count(); etc. etc.
        }
    }

    private fun createMyReqErrorListener(): Response.ErrorListener {
        return Response.ErrorListener { error ->
            var toast = Toast.makeText(this, error.message, Toast.LENGTH_LONG)
            toast.show()
            txtView1?.text=error.message
            // Do whatever you want to do with error.getMessage();
        }
    }

}

interface PostServices {

    @GET("posts")
    fun getPosts(): Call<List<Barang>>

}
