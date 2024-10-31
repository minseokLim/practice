package com.minseoklim.toyproject2024.payment.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    indexes = [Index(columnList = "member_id")],
    uniqueConstraints = [UniqueConstraint(columnNames = ["payment_uid"])]
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
abstract class Payment(
    amount: Long,
    productName: String,
    memberId: Int
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    val amount: Amount = Amount(amount)

    val productName: ProductName = ProductName(productName)

    val memberId: Int = memberId

    val paymentUid: PaymentUid = PaymentUid()

    var isCanceled: Boolean = false
        protected set

    fun checkAuthority(memberId: Int) {
        if (this.memberId != memberId) {
            throw NoPermissionException(ErrorCode.NO_PAYMENT_PERMISSION)
        }
    }

    fun cancel() {
        isCanceled = true
    }

    fun hasAmount(amount: Long): Boolean {
        return this.amount.value.toLong() == amount
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}
