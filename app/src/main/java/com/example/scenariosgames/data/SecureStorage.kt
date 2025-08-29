package com.example.securetasks.data
private fun readAll(): MutableList<Task> {
val raw = prefs.getString("tasks", null) ?: return mutableListOf()
return try {
json.decodeFromString(
ListSerializer(Task.serializer()),
raw
).toMutableList()
} catch (e: Exception) {
// Redacted signal (no payload leakage)
ThreatSignals.emit(
event = "storage_parse_error",
meta = mapOf("exception" to e.javaClass.simpleName)
)
mutableListOf()
}
}


private fun writeAll(list: List<Task>) {
val raw = json.encodeToString(ListSerializer(Task.serializer()), list)
prefs.edit().putString("tasks", raw).apply()
}


fun getAll(): List<Task> = readAll()


fun saveAll(list: List<Task>) = writeAll(list)


fun addTask(title: String, notes: String?): Task {
val safeTitle = validateTitle(title)
val safeNotes = validateNotes(notes)
val now = System.currentTimeMillis()
val task = Task(
id = UUID.randomUUID().toString(),
title = safeTitle,
notes = safeNotes,
createdAt = now,
done = false
)
val list = readAll()
list.add(task)
writeAll(list)
ThreatSignals.emit("task_created", mapOf("title_len" to safeTitle.length.toString()))
return task
}


private fun validateTitle(input: String): String {
val trimmed = input.trim()
require(trimmed.isNotEmpty()) { "Title is required" }
require(trimmed.length <= 64) { "Title too long" }
require(trimmed.none { it.code < 0x20 }) { "Invalid chars" }
return trimmed
}


private fun validateNotes(input: String?): String? {
if (input == null) return null
val trimmed = input.trim()
require(trimmed.length <= 300) { "Notes too long" }
return trimmed
}
}
