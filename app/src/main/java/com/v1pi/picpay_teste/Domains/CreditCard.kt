package com.v1pi.picpay_teste.Domains

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class CreditCard(@PrimaryKey(autoGenerate = true) val uid: Int, @ColumnInfo(name = "number") val number: String, @ColumnInfo(name = "cvv") val cvv: Int, @ColumnInfo(name = "expiry_date") val expiry_date: String) {

}