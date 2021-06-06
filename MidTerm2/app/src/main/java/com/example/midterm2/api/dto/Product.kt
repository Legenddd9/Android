package com.example.midterm2.api.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PRODUCTS")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "PRODUCT_ID")
    val id: Long?,

    @ColumnInfo(name = "PRODUCT_NAME")
    val name: String?,

    @ColumnInfo(name = "PRODUCT_DESCRIPTION")
    val description: String?,

    @ColumnInfo(name = "PRODUCT_IMAGE")
    val image: String?
)

data class AboutProduct<T>(
    val total: Int?,
    val data: T
)
