package com.unison.appproducto.data

import com.unison.appproducto.models.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productsDatabaseDao: ProductDatabaseDao) {
    val allProducts: Flow<List<Product>> = productsDatabaseDao.getProducts()

    fun getProductById(id: Int): Flow<Product> {
        return productsDatabaseDao.getProductById(id)
    }

    suspend fun insertProduct(product: Product) {
        productsDatabaseDao.addProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        productsDatabaseDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productsDatabaseDao.deleteProduct(product)
    }
}