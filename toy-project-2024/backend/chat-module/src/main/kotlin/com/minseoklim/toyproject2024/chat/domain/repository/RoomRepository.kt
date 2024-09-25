package com.minseoklim.toyproject2024.chat.domain.repository

import com.minseoklim.toyproject2024.chat.domain.model.Room
import org.springframework.data.jpa.repository.JpaRepository

interface RoomRepository : JpaRepository<Room, Long>
