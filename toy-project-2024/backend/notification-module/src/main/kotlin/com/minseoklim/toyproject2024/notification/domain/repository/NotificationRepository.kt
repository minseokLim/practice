package com.minseoklim.toyproject2024.notification.domain.repository

import com.minseoklim.toyproject2024.notification.domain.model.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long>
