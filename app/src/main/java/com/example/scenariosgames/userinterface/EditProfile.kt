package com.example.scenariosgames.userinterface


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R
class EditProfile : ComponentActivity() {
    private lateinit var savingbutton:Button
    private lateinit var usernameEdit: EditText
    private lateinit var EmailAdressEdit:EditText
    private  lateinit var GenderEdit:EditText
    private lateinit var CityEdit:EditText
    private lateinit var phoneEdit:EditText
    private lateinit var profileimage:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.editprofile)
           usernameEdit = findViewById(R.id.editTextUsername)
           EmailAdressEdit = findViewById(R.id.editTextTextEmailAdress)
           GenderEdit = findViewById(R.id.editTextTextMultiLine)
          CityEdit = findViewById(R.id.CityTextEdit)
          phoneEdit = findViewById(R.id.editTextPhone)

         profileimage =findViewById(R.id.profile)


         savingbutton.setOnClickListener{

          val username = usernameEdit.text
             username.toString()

             val Email = EmailAdressEdit.text
             Email.toString()

            handleButtonClick()
         }
    }

    private fun handleButtonClick() {

    }
}