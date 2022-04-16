package me.brisson.apitest.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import me.brisson.apitest.data.model.CustomerBody
import me.brisson.apitest.data.model.CustomerResponse
import me.brisson.apitest.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getAllCustomers()
        renderMainState()
        setupClickListener()
    }

    private fun renderMainState() {
        viewModel.getMainState().observe(this) { state ->
            when (state) {
                is MainState.IsIdling -> {}
                is MainState.IsLoading -> {}
                is MainState.OnGetAllCustomersSuccess -> {
                    setupCustomersAdapter(state.customerResponses)
                }
                is MainState.OnError -> {
                    Log.e(TAG, "renderMainState: OnError: ${state.message}")
                }
                is MainState.OnCreateCustomer -> {
                    viewModel.getAllCustomers()
                }
                is MainState.OnDeleteCustomer -> {
                    viewModel.getAllCustomers()
                }
                else -> Unit
            }
        }
    }

    private fun setupClickListener() {
        with(binding) {
            imageReload.setOnClickListener {
                viewModel.getAllCustomers()
            }

            buttonAddCustomer.setOnClickListener {
                viewModel.addCustomer(CustomerBody("makoto", "mateus", "makoto@mail.com"))
            }
        }
    }

    private fun setupCustomersAdapter(customerResponses: List<CustomerResponse>) {
        binding.recyclerCustomers.adapter = CustomerAdapter(
            customerResponses,
            onDeleteClick = { customer ->
                viewModel.deleteCustomer(customer)
            }
        )
    }

    companion object {
        val TAG: String = MainActivity::class.java.name
    }
}