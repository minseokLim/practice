package com.minseoklim.toyproject2024.card.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.util.CommonUtil.entityEmbeddableEquals
import com.minseoklim.toyproject2024.common.util.CommonUtil.entityHashCode
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "member_id")])
class Card(
    cardNumber: String,
    cardExpiry: String,
    birth: String,
    pwd2digit: String,
    issuerName: String,
    memberId: Int
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    val cardNumber: CardNumber = CardNumber(cardNumber)

    val cardExpiry: CardExpiry = CardExpiry(cardExpiry)

    val birth: Birth = Birth(birth)

    val pwd2digit: Pwd2digit = Pwd2digit(pwd2digit)

    val issuerName: IssuerName = IssuerName(issuerName)

    val memberId: Int = memberId

    var isDeleted: Boolean = false
        protected set

    fun delete() {
        isDeleted = true
    }

    final override fun equals(other: Any?): Boolean {
        return this.entityEmbeddableEquals(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.entityHashCode()
}
