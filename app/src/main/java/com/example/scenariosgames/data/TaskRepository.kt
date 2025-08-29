package com.example.securetasks.data


import com.example.securetasks.model.Task
import com.example.securetasks.security.ThreatSignals


class TaskRepository(private val storage: SecureStorage) {
fun list(): List<Task> = storage.getAll()


fun add(title: String, notes: String?): Task {
ThreatSignals.emit("task_add_attempt", mapOf("title_len" to title.length.toString()))
return storage.addTask(title, notes)
}


fun toggleDone(id: String): Boolean {
val list = storage.getAll().toMutableList()
val idx = list.indexOfFirst { it.id == id }
if (idx == -1) return false
val t = list[idx]
list[idx] = t.copy(done = !t.done)
storage.saveAll(list)
ThreatSignals.emit("task_toggle", mapOf("done" to list[idx].done.toString()))
return true
}


fun delete(id: String): Boolean {
val list = storage.getAll().toMutableList()
val removed = list.removeIf { it.id == id }
if (removed) {
storage.saveAll(list)
ThreatSignals.emit("task_deleted")
}
return removed
}


fun clear() {
storage.saveAll(emptyList())
ThreatSignals.emit("task_cleared")
}


fun search(query: String): List<Task> {
val q = query.trim().take(64)
if (q.isEmpty()) return list()
return storage.getAll().filter {
it.title.contains(q, ignoreCase = true) ||
(it.notes?.contains(q, ignoreCase = true) ?: false)
}
}
}
