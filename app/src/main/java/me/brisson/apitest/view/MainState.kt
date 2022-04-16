package me.brisson.apitest.view

import me.brisson.apitest.data.model.CustomerResponse

sealed class MainState {
    object IsIdling : MainState()
    object IsLoading : MainState()
    data class OnGetAllCustomersSuccess(val customerResponses: List<CustomerResponse>) : MainState()
    data class OnCreateCustomer(val customer: CustomerResponse?) : MainState()
    data class OnDeleteCustomer(val customer: CustomerResponse) : MainState()
    data class OnError(val message: String) : MainState()
}
