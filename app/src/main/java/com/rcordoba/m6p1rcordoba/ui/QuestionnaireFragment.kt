package com.rcordoba.m6p1rcordoba.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.rcordoba.m6p1rcordoba.R
import com.rcordoba.m6p1rcordoba.application.OrdersDBApp
import com.rcordoba.m6p1rcordoba.data.OrderRepo
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity
import com.rcordoba.m6p1rcordoba.databinding.FragmentQuestionnaireBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionnaireFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionnaireFragment : Fragment() {
    private lateinit var binding : FragmentQuestionnaireBinding

    private lateinit var repository : OrderRepo

    private lateinit var order : OrderEntity

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = (requireContext().applicationContext as OrdersDBApp).repository
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionnaireBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createButton.setOnClickListener{
            order = OrderEntity(
                0,
                binding.mainDishEditText.text.toString(),
                binding.beverageEditText.text.toString(),
                binding.dessertEditText.text.toString(),
                0
            )

            try {
                lifecycleScope.launch {

                    val result = async {
                        repository.createOrder(order)

                    }

                    result.await()

                    withContext(Dispatchers.Main){
                    }
                    Toast.makeText(requireContext(),getString(R.string.toast_create_success),Toast.LENGTH_SHORT).show()
                }

            }catch(e: IOException) {
                e.printStackTrace()
                Toast.makeText(requireContext(),
                    getString(R.string.toast_create_error),Toast.LENGTH_SHORT).show()

            }



        }

        binding.showRecyclerButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,orderListFragment.newInstance())
                .addToBackStack("orderListFragment")
                .commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment QuestionnaireFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            QuestionnaireFragment()
    }
}