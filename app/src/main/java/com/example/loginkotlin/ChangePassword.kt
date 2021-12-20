package com.example.loginkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginkotlin.databinding.ActivityChangePasswardBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class ChangePassword : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
//        setContentView(R.layout.activity_change_passward)
        binding = ActivityChangePasswardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btSubmit.setOnClickListener{
            user_change_password()
        }
    }

    private fun user_change_password() {
        val stringPasswordFirst: String = binding.etNewPassword.text.toString()
        val stringPasswordSecond: String = binding.etNewPasswordSecond.text.toString()
        if (stringPasswordFirst == "") {
            binding.etNewPassword.error = "password is required"
            binding.etNewPassword.requestFocus()
        } else if (stringPasswordSecond == "") {
            binding.etNewPasswordSecond.error = ("please confirm your password")
            binding.etNewPasswordSecond.requestFocus()
        } else if (stringPasswordFirst != stringPasswordSecond) {
            binding.etNewPasswordSecond.error = ("the second one did not match")
            binding.etNewPasswordSecond.requestFocus()
        } else {
            val user = FirebaseAuth.getInstance().currentUser
            user?.updatePassword(stringPasswordFirst)?.addOnCompleteListener { task: Task<Void?> ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@ChangePassword,
                        "Successfully Changed Password!",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@ChangePassword, LoginActivity::class.java))
                }
            }
        }
    }
}