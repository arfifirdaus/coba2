package com.example.coba2.adapter

import android.app.Activity
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.coba2.R
import com.example.coba2.model.Barang

class CustomAdapter(context: Context, resource: Int, private val list: List<Barang>) :
    ArrayAdapter<Barang>(context, resource) {

    class viewHolder{
        var txtVKodeProduk: TextView? = null
        var txtVNamaProduk: TextView? = null
    }

    override fun getItem(position: Int): Barang? {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)
        var convertView2: View? = convertView
        var holder: viewHolder? = null
        var item: Barang? = getItem(position)

        var inflater: LayoutInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if(convertView==null)
        {
            convertView2 = inflater.inflate(R.layout.custom_list, null)
            holder = viewHolder()
            holder.txtVKodeProduk =convertView2.findViewById(R.id.textViewKodeBarang)
            holder.txtVNamaProduk = convertView2.findViewById(R.id.textViewNamaBarang)
            convertView2.setTag(holder)
        } else {
            holder =convertView2!!.getTag() as viewHolder
        }

        holder.txtVKodeProduk!!.setText(item!!.kodeBarang)
        holder.txtVNamaProduk!!.setText(item!!.namaBarang)

        return convertView2!!
    }
}