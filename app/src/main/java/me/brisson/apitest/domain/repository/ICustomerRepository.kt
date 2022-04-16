package me.brisson.apitest.domain.repository

import me.brisson.apitest.data.model.CustomerBody
import me.brisson.apitest.data.model.CustomerResponse

interface ICustomerRepository {

    suspend fun getAllCustomers() : List<CustomerResponse>

    suspend fun deleteCustomer(id: String) : String

    suspend fun createCustomer(customer: CustomerBody) : CustomerResponse?
}