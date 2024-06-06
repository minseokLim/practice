package com.minseoklim.toyproject2024.common.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {
    @CreatedDate
    var createdDateTime: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    var modifiedDateTime: LocalDateTime = LocalDateTime.now()
        protected set
}
