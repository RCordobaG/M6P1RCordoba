package com.rcordoba.m6p1rcordoba.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.rcordoba.m6p1rcordoba.R
import com.rcordoba.m6p1rcordoba.application.OrdersDBApp
import com.rcordoba.m6p1rcordoba.data.OrderRepo
import com.rcordoba.m6p1rcordoba.data.db.model.OrderEntity
import com.rcordoba.m6p1rcordoba.databinding.OrderDialogBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class OrderDialog(
    private val newOrder: Boolean = true,
    private var order: OrderEntity = OrderEntity(
        id = 0,
        mainCourse = "",
        beverage = "",
        dessert = "",
        tableNumber = 1,
        waiterName = ""
    ),
    private val updateUI: () -> Unit,
    private val message: (String) -> Unit
) : DialogFragment() {

    private var _binding: OrderDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: Dialog

    private var saveButton: Button? = null

    private lateinit var repository: OrderRepo

    //Aquí se crea y configura el diálogo de forma inicial
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = OrderDialogBinding.inflate(requireActivity().layoutInflater)

        builder = AlertDialog.Builder(requireContext())

        repository = (requireContext().applicationContext as OrdersDBApp).repository

        binding.apply {
            binding.ivHeader.setText(order.id.toString())
            binding.tietTitle.setText(order.mainCourse)
            binding.tietGenre.setText(order.beverage)
            binding.tietDeveloper.setText(order.dessert)
        }

        dialog = if (newOrder)
            buildDialog(getString(R.string.dialog_save_buttonm),
                getString(R.string.dialog_cancel_button), {
                //Acción guardar
                order.apply {
                    mainCourse = binding.tietTitle.text.toString()
                    beverage = binding.tietGenre.text.toString()
                    dessert = binding.tietDeveloper.text.toString()
                }
                try {
                    lifecycleScope.launch {

                        val result = async{
                            repository.createOrder(order)
                        }

                        result.await()

                        withContext(Dispatchers.Main){
                            message(getString(R.string.dialog_order_created_success))

                            updateUI()
                        }

                    }

                }catch(e: IOException){
                    e.printStackTrace()

                    message(getString(R.string.dialog_error_creating_order))

                }
            }, {
                //Acción cancelar

            })
        else
            buildDialog(getString(R.string.dialog_update_button),
                getString(R.string.dialog_delete_button), {
                //Acción actualizar
                order.apply {
                    mainCourse = binding.tietTitle.text.toString()
                    beverage = binding.tietGenre.text.toString()
                    dessert = binding.tietDeveloper.text.toString()
                }
                try {
                    lifecycleScope.launch {
                        val result = async {
                            repository.updateOrder(order)
                        }

                        result.await()

                        withContext(Dispatchers.Main){
                            message(getString(R.string.dialog_order_update_success))
                            updateUI()
                        }
                    }

                }catch(e: IOException) {
                    e.printStackTrace()

                    message(getString(R.string.dialog_order_update_error))

                }
            }, {
                //Acción borrar
                val context = requireContext()

                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.dialog_confirm_delete))
                    .setMessage(getString(R.string.dialog_confirm_delete_id, order.id))
                    .setPositiveButton(getString(R.string.dialog_accept)){ _, _ ->
                        try {
                            lifecycleScope.launch {

                                val result = async {
                                    repository.deleteOrder(order)
                                }

                                result.await()

                                withContext(Dispatchers.Main){
                                    //message(context.getString(R.string.removedGame))
                                    message(getString(R.string.dialog_delete_success))

                                    updateUI()
                                }
                            }

                        }catch(e: IOException) {
                            e.printStackTrace()

                            message(getString(R.string.dialog_delete_error))

                        }
                    }
                    .setNegativeButton(getString(R.string.dialog_delete_cancel)){ dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            })

        return dialog
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Se va a llamar después de que se muestra el diálogo en pantalla
    override fun onStart() {
        super.onStart()


        val alertDialog = dialog as AlertDialog
        saveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        saveButton?.isEnabled = false

        /*binding.tietTitle.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                //Aquí ya perdió el foco esa vista

            }
        }*/

        binding.tietTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                saveButton?.isEnabled = validateFields()
            }

        })

        binding.tietGenre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                saveButton?.isEnabled = validateFields()
            }

        })

        binding.tietDeveloper.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                saveButton?.isEnabled = validateFields()
            }

        })

    }

    private fun validateFields(): Boolean =
        (binding.tietTitle.text.toString().isNotEmpty() &&
                binding.tietGenre.text.toString().isNotEmpty() &&
                binding.tietDeveloper.text.toString().isNotEmpty())

    private fun buildDialog(
        btn1Text: String,
        btn2Text: String,
        positiveButton: () -> Unit,
        negativeButton: () -> Unit
    ): Dialog =
        builder.setView(binding.root)
            .setTitle(getString(R.string.order))
            .setPositiveButton(btn1Text) { _, _ ->
                //Acción para el botón positivo
                positiveButton()
            }
            .setNegativeButton(btn2Text) { _, _ ->
                //Acción para el botón negativo
                negativeButton()
            }
            .create()

}