package com.unison.appproducto

import android.app.Application
import com.unison.appproducto.data.ProductDatabase
import com.unison.appproducto.data.ProductRepository

class YourApp : Application() {
    private val database by lazy { ProductDatabase.getDatabase(this) }
    val repository by lazy { ProductRepository(database.productsDao()) }

    companion object {
        lateinit var instance: YourApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}