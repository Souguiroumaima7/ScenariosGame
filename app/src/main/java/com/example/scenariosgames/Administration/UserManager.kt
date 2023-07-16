package com.example.scenariosapplication.Administration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.R

class UserManager  : AppCompatActivity(){

    data class User(val id: Int, var name: String, var email: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_update)

    }
    private val users: MutableList<User> = mutableListOf()

    fun createUser(name: String, email: String) {
        val newUser = User(getNextUserId(), name, email)
        users.add(newUser)
    }

    fun updateUser(id: Int, newName: String, newEmail: String) {
        val user = getUserById(id)
        user?.apply {
            name = newName
            email = newEmail
        }
    }

    fun deleteUser(id: Int) {
        val user = getUserById(id)
        user?.let {
            users.remove(it)
        }
    }

    fun getUserById(id: Int): User? {
        return users.find { it.id == id }
    }

    private fun getNextUserId(): Int {
        return if (users.isEmpty()) {
            1
        } else {
            users.last().id + 1
        }
    }
}

fun main() {
    val userManager = UserManager()

    // Créer un utilisateur
    userManager.createUser("John Doe", "john.doe@example.com")

    // Modifier un utilisateur
    userManager.updateUser(1, "Jane Smith", "jane.smith@example.com")

    // Supprimer un utilisateur
    userManager.deleteUser(1)
}
