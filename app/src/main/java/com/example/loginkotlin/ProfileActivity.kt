package com.example.loginkotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginkotlin.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
//    var user: FirebaseUser? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_profile)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvWelcome.text = "Hello, " + FirebaseAuth.getInstance().currentUser!!.email
        binding.btChangePassword.setOnClickListener{
            val intent = Intent(this, ChangePassword::class.java)
            startActivity(intent)
//            finish()
        }
        binding.btLogout.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
//            finish()
        }
    }
}