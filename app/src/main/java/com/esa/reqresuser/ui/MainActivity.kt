package com.esa.reqresuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.esa.reqresuser.adapter.LoadingStateAdapter
import com.esa.reqresuser.adapter.UserAdapter
import com.esa.reqresuser.databinding.ActivityMainBinding
import com.esa.reqresuser.ui.viewmodel.MainViewModel
import com.esa.reqresuser.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupViewModel()
        setupView()
    }

    private fun setupView() {
        userAdapter = UserAdapter()
        binding.rvUser.adapter = userAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                userAdapter.retry()
            }
        )
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        vm.getUsers().observe(this){
            userAdapter.submitData(lifecycle, it)
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        vm = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }
}