package com.minseoklim.toyproject2024.chat.domain.mapper

import org.apache.ibatis.annotations.Mapper

@Mapper
interface ChatMapper {
    fun selectUnreadMessageCounts(params: List<Map<String, Any?>>): List<UnreadMessageCount>
}
