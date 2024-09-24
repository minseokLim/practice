package com.minseoklim.toyproject2024.order.application.converter

import com.minseoklim.toyproject2024.order.domain.model.Product
import com.minseoklim.toyproject2024.product.dto.application.QueryProductOutput

object ProductConverter {
    fun from(output: QueryProductOutput): Product {
        return Product(
            id = output.id,
            name = output.name,
            price = output.price,
            stockQuantity = output.stockQuantity,
            isDeleted = output.isDeleted
        )
    }
}
