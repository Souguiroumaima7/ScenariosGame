package com.example.scenariosapplication.UserInterface

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class EmailConnection : ComponentActivity() {

    private val TAG = "EmailPassword"

    // [START declare_auth]
    private var mAuth: FirebaseAuth? = null
    // [END declare_auth]

    // [END declare_auth]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]
    }

    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            reload()
        }
    }
    // [END on_start_check_user]

    // [END on_start_check_user]
    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, object : OnCompleteListener<AuthResult?> {
                override fun onComplete(task: Task<AuthResult?>) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException())
                        Toast.makeText(
                            this@EmailConnection, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
            })
        // [END create_user_with_email]
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, object : OnCompleteListener<AuthResult?> {
                override fun onComplete(task: Task<AuthResult?>) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException())
                        Toast.makeText(
                            this@EmailConnection, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
            })
        // [END sign_in_with_email]
    }

    private fun sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        val user = mAuth!!.currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener(this, object : OnCompleteListener<Void?> {
                override fun onComplete(task: Task<Void?>) {
                    // Email sent
                }
            })
        // [END send_email_verification]
    }

    private fun reload() {}

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User is signed in, perform actions for signed-in user
            user.uid
            user.email
            // ... Perform any other necessary operations with the user object
            // For example, you can update UI elements, start a new activity, etc.
        }
    }

}