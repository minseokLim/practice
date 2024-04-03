package com.minseoklim.queue.config

import com.minseoklim.queue.domain.QueueTokenUtil
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.time.LocalDateTime

@Configuration
@EnableScheduling
class ScheduleConfiguration(
    private val queueTokenUtil: QueueTokenUtil
) {
    @Scheduled(cron = "0 0 0 * * ?")
    fun deleteStaleToken() {
        // 하루 이상 지난 토큰 삭제
        queueTokenUtil.deleteTokenBefore(LocalDateTime.now().minusDays(1))
    }
}
