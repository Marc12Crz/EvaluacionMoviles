package com.marcelo.cristhian.poketinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marcelo.cristhian.poketinder.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtConfirmPassword.text.toString()

            if (!isValidEmail(email)) {
                Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 8) {
                Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            saveUserData(email, password)
            goToLogin()
        }


        binding.btnAlreadyHaveAccount.setOnClickListener {
            goToLogin()
        }


        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    private fun saveUserData(email: String, password: String) {
        val sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_KEY", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("USER_EMAIL_KEY", email)
            .putString("USER_PASSWORD_KEY", password)
            .apply()
        Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
