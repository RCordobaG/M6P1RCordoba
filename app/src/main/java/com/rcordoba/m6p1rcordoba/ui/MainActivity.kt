package com.rcordoba.m6p1rcordoba.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rcordoba.m6p1rcordoba.R
import com.rcordoba.m6p1rcordoba.application.OrdersDBApp
import com.rcordoba.m6p1rcordoba.data.db.OrderRepo
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity
import com.rcordoba.m6p1rcordoba.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var orders : List<OrderEntity> = emptyList()
    private lateinit var repository : OrderRepo

    private lateinit var orderAdapter: OrderAdapter

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