package com.rcordoba.m6p1rcordoba.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rcordoba.m6p1rcordoba.R
import com.rcordoba.m6p1rcordoba.application.OrdersDBApp
import com.rcordoba.m6p1rcordoba.data.OrderRepo
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity
import com.rcordoba.m6p1rcordoba.databinding.FragmentOrderListBinding
import com.rcordoba.m6p1rcordoba.databinding.FragmentQuestionnaireBinding
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [orderListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class orderListFragment : Fragment() {

    private lateinit var orderAdapter : OrderAdapter
    private lateinit var binding: FragmentOrderListBinding

    private lateinit var orders : List<OrderEntity>

    private lateinit var repository : OrderRepo
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderAdapter = OrderAdapter() {selectedOrder ->

        }
        repository = (requireContext().applicationContext as OrdersDBApp).repository


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderListBinding.inflate(inflater,container,false)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }
        updateUI()
        return binding.root
    }

    private fun updateUI(){
        lifecycleScope.launch {
            orders = repository.getAllOrders()

            orderAdapter.updateList(orders)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment orderListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            orderListFragment()
    }
}