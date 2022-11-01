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

class LoginActivity : AppCompatActivity() {

    private var txtEmail: EditText? = null
    private var txtPassword: EditText? = null
    private var btnLoggin: Button? = null
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtEmail = findViewById(R.id.txt_email_login)
        txtPassword = findViewById(R.id.txt_password_login)
        btnLoggin = findViewById(R.id.btn_login_login)
        firebaseAuth = FirebaseAuth.getInstance()

        btnLoggin?.setOnClickListener {
            logginUser()
        }
    }

    private fun logginUser() {
        var email = txtEmail?.text.toString().trim()
        var password = txtPassword?.text.toString().trim()

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(
                applicationContext,
                "Email or Password can not be empty",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            firebaseAuth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "Authentication success.",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Nice", "signInWithEmail:success")
                    } else {
                        val error = task.exception?.message
                        Log.w("Error", "signInWithEmail:failure -> $error", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed -> $error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}