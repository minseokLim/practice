package com.minseoklim.toyproject2024.auth.domain.repository

import com.minseoklim.toyproject2024.auth.domain.model.LoginHistory
import org.springframework.data.jpa.repository.JpaRepository

interface LoginHistoryRepository : JpaRepository<LoginHistory, Long>
