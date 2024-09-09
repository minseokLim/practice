package com.minseoklim.toyproject2024.order.domain.service

import com.minseoklim.toyproject2024.order.domain.model.Product

object OrderNameGenerator {
    fun generate(products: List<Product>): String {
        require(products.isNotEmpty()) { "products is empty" }

        return if (products.size == 1) {
            products[0].name
        } else {
            "${products[0].name} 외 ${products.size - 1}종"
        }
    }
}
