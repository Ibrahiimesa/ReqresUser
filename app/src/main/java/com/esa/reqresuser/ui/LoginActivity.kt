package com.esa.reqresuser.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.esa.reqresuser.R
import com.esa.reqresuser.databinding.ActivityLoginBinding
import com.esa.reqresuser.databinding.ActivityMainBinding
import com.esa.reqresuser.ui.viewmodel.LoginViewModel
import com.esa.reqresuser.ui.viewmodel.MainViewModel
import com.esa.reqresuser.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var vm: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupVm()
        binding.btnLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            hideKeyboard()
            if (email.isNotEmpty() && password.isNotEmpty()){
                vm.login(email, password)
            } else{
                Snackbar.make(binding.root, "Check Your Input", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        vm.loginResult.observe(this) { result ->
            result.onSuccess {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.onFailure {e ->
                Snackbar.make(binding.root, "Login failed: User not Found ${e.message}", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setupVm() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        vm = ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}