package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class ResetActivity : AppCompatActivity() {

    private var txtReset: EditText? = null
    private var btnReset: Button? = null
    private var firebaseAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        txtReset = findViewById(R.id.txtReset)
        btnReset = findViewById(R.id.btnReset)
        firebaseAuth = FirebaseAuth.getInstance()

        btnReset?.setOnClickListener {
            resetPassword()
        }
    }

    private fun resetPassword() {
        val email = txtReset?.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Email cant be empty", Toast.LENGTH_LONG).show()
        } else {
            firebaseAuth?.sendPasswordResetEmail(email)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("nice", "Reset succesfully")
                    Toast.makeText(
                        applicationContext,
                        "Reset successfully",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val error = task.exception?.message
                    Toast.makeText(
                        applicationContext,
                        "Something went wrong, please check email",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.w("Error", "Error-> $error")
                }
            }
        }
    }
}