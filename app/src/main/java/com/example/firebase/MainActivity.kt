package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var btnSingUp: Button? = null
    private var btnLogin: Button? = null
    private var txtEmail: EditText? = null
    private var txtPassword: EditText? = null
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSingUp = findViewById(R.id.btn_signup)
        btnLogin = findViewById(R.id.btn_login)
        txtEmail = findViewById(R.id.txt_email)
        txtPassword = findViewById(R.id.txt_password)

        firebaseAuth = FirebaseAuth.getInstance()

        btnSingUp?.setOnClickListener {
            addUser()
        }

    }

    private fun addUser() {
        var email = txtEmail?.text.toString().trim()
        var password = txtPassword?.text.toString().trim()

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(
                applicationContext,
                "Email or Password can not be empty",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            firebaseAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener {
                object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Account created",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val error = task.exception?.message
                            Toast.makeText(
                                applicationContext,
                                "A problem has ocurred",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}