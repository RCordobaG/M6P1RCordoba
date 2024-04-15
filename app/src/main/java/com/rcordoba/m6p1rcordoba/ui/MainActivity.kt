package com.rcordoba.m6p1rcordoba.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rcordoba.m6p1rcordoba.R
import com.rcordoba.m6p1rcordoba.application.OrdersDBApp
import com.rcordoba.m6p1rcordoba.data.OrderRepo
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity
import com.rcordoba.m6p1rcordoba.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var repository : OrderRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = (application as OrdersDBApp).repository

    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView,QuestionnaireFragment.newInstance())
            .commit()
    }
}