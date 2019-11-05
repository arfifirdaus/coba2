package com.example.coba2.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.coba2.R
import com.example.coba2.model.Barang

class CustomAdapter2(private val context: Activity, private val barang: List<Barang>)
    : ArrayAdapter<String>(context, R.layout.custom_list) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val kodeBarang = rowView.findViewById(R.id.textViewKodeBarang) as TextView
        val namaBarang = rowView.findViewById(R.id.textViewNamaBarang) as TextView

        kodeBarang.text = barang[position].kodeBarang
        namaBarang.text = barang[position].namaBarang

        return rowView
    }
}