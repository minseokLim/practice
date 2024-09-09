package com.minseoklim.toyproject2024.order.domain.service

import com.minseoklim.toyproject2024.order.domain.model.OrderProduct
import com.minseoklim.toyproject2024.order.domain.model.Product

object TotalAmountCalculator {
    fun calculate(orderProducts: List<OrderProduct>, products: List<Product>): Long {
        require(orderProducts.isNotEmpty()) { "orderProducts is empty" }
        require(products.map { it.id }.containsAll(orderProducts.map { it.productId })) { "product is not exist" }

        val productMap = products.associateBy { it.id }
        return orderProducts.sumOf { productMap[it.productId]!!.price * it.quantity }
    }
}
