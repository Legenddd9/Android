package com.example.midterm2.api.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM PRODUCTS")
    fun getAllProducts(): List<Product>?

    @Query("SELECT * FROM PRODUCTS P WHERE P.PRODUCT_ID == :carId ")
    fun getProductById(carId : Int): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg products: Product)

    @Query("DELETE FROM PRODUCTS")
    fun deleteAll()
}