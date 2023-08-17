package com.example.scenariosgames.userinterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var EmailEditText:EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        nameEditText = findViewById(R.id.editTextName)
        EmailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        signupButton = findViewById(R.id.buttonsign)

        // Définir un clic d'écouteur pour le bouton d'inscription
        signupButton.setOnClickListener {
            val name = nameEditText.text.toString()
            EmailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Vérifier les informations d'inscription
            if (validateSignup(name,password)) {
                // Afficher un message de succès
                Toast.makeText(this, "Inscription réussie", Toast.LENGTH_SHORT).show()
            } else {
                // Afficher un message d'erreur si les informations d'inscription sont incorrectes
                Toast.makeText(this, "Échec de l'inscription", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validateCredentials(username: String, password: String): Boolean {
        // Vérifier les informations de connexion (remplacez cette logique par votre propre vérification)
        return username == "utilisateur" && password == "motdepasse"
    }

    private fun validateSignup(username: String, password: String): Boolean {
        // Vérifier les informations d'inscription (remplacez cette logique par votre propre vérification)
        return username.isNotEmpty() && password.isNotEmpty()
        }

}
