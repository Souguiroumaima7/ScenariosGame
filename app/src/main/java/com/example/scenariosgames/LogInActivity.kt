package com.example.scenariosgames
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosapplication.UserInterface.HomeActivity
import com.example.scenariosgames.R

class LogInActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Récupérer les références des vues
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        // Définir un clic d'écouteur pour le bouton de connexion
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Vérifier les informations de connexion
            if (validateCredentials(username, password)) {
                // Rediriger vers l'activité principale après une connexion réussie
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Afficher un message d'erreur si les informations de connexion sont incorrectes
                Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        // Vérifier les informations de connexion (remplacez cette logique par votre propre vérification)
        return username == "utilisateur" && password == "motdepasse"

    }
}