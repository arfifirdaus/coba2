package com.example.coba2.model

import com.google.gson.annotations.SerializedName

data class Barang (@SerializedName("id")
                   var id: String? = null,
                   @SerializedName("kode_barang")
                   var kodeBarang: String? = null,
                   @SerializedName("nama_barang")
                   var namaBarang: String? = null,
                   @SerializedName("deskripsi")
                   var deskripsi: String? = null,
                   @SerializedName("stok")
                   var stok: String? = null)