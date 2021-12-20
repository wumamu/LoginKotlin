package com.example.loginkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginkotlin.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

//import kotlinx.android.synthetic.main.act_findview_layout.*

class LoginActivity : AppCompatActivity() {
//    private var myButtonForID: Button? = null
    private lateinit var binding: ActivityLoginBinding
    private val mAuth: FirebaseAuth? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btLogin.setOnClickListener{
            user_login()

        }
    }

    private fun user_login() {
        val stringUserEmail: String = binding.etUsername.text.toString()
        val stringPassword: String = binding.etPassword.text.toString()
        if (stringUserEmail.equals("")) {
            binding.etUsername.setError("Email is required")
            binding.etUsername.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(stringUserEmail).matches()) {
            binding.etUsername.setError("Please enter a valid Email")
            binding.etUsername.requestFocus()
            return
        }
        if (stringPassword.equals("")) {
            binding.etPassword.setError("Password is required")
            binding.etPassword.requestFocus()
            return
        }

        mAuth!!.signInWithEmailAndPassword(stringUserEmail, stringPassword).addOnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show()
                }
        }
    }
}