package com.minseoklim.cqrspractice.repository

import com.minseoklim.cqrspractice.event.Event
import com.minseoklim.cqrspractice.event.UserEvent
import com.minseoklim.cqrspractice.projector.UserProjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.stereotype.Component
import java.util.LinkedList
import java.util.Queue

@Component
class EventStore(
    private val userProjector: UserProjector
) {
    private val userEventStore = mutableMapOf<String, MutableList<Event>>()
    private val queue: Queue<Event> = LinkedList()

    fun addUserEvent(event: UserEvent) {
        userEventStore[event.userId]?.add(event) ?: userEventStore.put(event.userId, mutableListOf(event))
        queue.offer(event)
        project()
    }

    fun addUserEvents(events: Collection<UserEvent>) {
        if (events.isNotEmpty()) {
            val userId = events.first().userId
            userEventStore[userId]?.addAll(events) ?: userEventStore.put(userId, ArrayList(events))
            queue.addAll(events)
            project()
        }
    }

    fun getUserEvents(userId: String): List<Event> {
        return userEventStore[userId] ?: emptyList()
    }

    private fun project() {
        CoroutineScope(Dispatchers.Default).launch {
            while (queue.isNotEmpty()) {
                val polledEvent = queue.poll()
                userProjector.project(polledEvent)
            }
        }
    }
}
