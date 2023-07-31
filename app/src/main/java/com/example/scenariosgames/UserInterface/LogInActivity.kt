package com.example.scenariosgames.UserInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosapplication.UserInterface.HomeActivity
import com.example.scenariosgames.R

class LogInActivity : AppCompatActivity() {

    private lateinit var EmailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var ForgotPassword:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Récupérer les références des vues
        EmailEditText = findViewById(R.id.EmailEditAdress)
        passwordEditText = findViewById(R.id.PasswordEdit)
        loginButton = findViewById(R.id.loginButton)
        ForgotPassword = findViewById(R.id.ForgotPassword)
        // Définir un clic d'écouteur pour le bouton de connexion
        loginButton.setOnClickListener {
            val username = EmailEditText.text.toString()
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
        ForgotPassword.setOnClickListener{
             val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        // Vérifier les informations de connexion (remplacez cette logique par votre propre vérification)
        return username == "utilisateur" && password == "motdepasse"

    }
}