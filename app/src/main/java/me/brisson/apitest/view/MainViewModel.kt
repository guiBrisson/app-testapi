package me.brisson.apitest.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.brisson.apitest.data.model.CustomerBody
import me.brisson.apitest.data.model.CustomerResponse
import me.brisson.apitest.domain.common.Resource
import me.brisson.apitest.domain.use_case.AllCustomersUseCase
import me.brisson.apitest.domain.use_case.CreateCustomerUseCase
import me.brisson.apitest.domain.use_case.DeleteCustomerUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val allCustomersUseCase: AllCustomersUseCase,
    private val deleteCustomerUseCase: DeleteCustomerUseCase,
    private val createCustomerUseCase: CreateCustomerUseCase
) : ViewModel() {

    private val _mainState = MutableLiveData<MainState>().apply { value = MainState.IsIdling }

    fun getMainState() : LiveData<MainState> = _mainState

    fun getAllCustomers() {
        allCustomersUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mainState.value = MainState.IsLoading
                }
                is Resource.Success -> {
                    val customers = result.data ?: listOf()
                    _mainState.value = MainState.OnGetAllCustomersSuccess(customers)
                }
                is Resource.Error -> {
                    _mainState.value = MainState.OnError(result.message ?: "There was an error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addCustomer(customer: CustomerBody) {
        createCustomerUseCase(customer).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mainState.value = MainState.IsLoading
                }
                is Resource.Success -> {
                    _mainState.value = MainState.OnCreateCustomer(result.data)
                }
                is Resource.Error -> {
                    _mainState.value = MainState.OnError(result.message ?: "There was an error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteCustomer(customer: CustomerResponse) {
        deleteCustomerUseCase(customer.id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _mainState.value = MainState.IsLoading
                }
                is Resource.Success -> {
                    _mainState.value = MainState.OnDeleteCustomer(customer)
                }
                is Resource.Error -> {
                    _mainState.value = MainState.OnError(result.message ?: "There was an error")
                }
            }
        }.launchIn(viewModelScope)
    }
}