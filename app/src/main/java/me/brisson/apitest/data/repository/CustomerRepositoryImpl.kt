package me.brisson.apitest.data.repository

import me.brisson.apitest.data.ITestApi
import me.brisson.apitest.data.model.CustomerBody
import me.brisson.apitest.data.model.CustomerResponse
import me.brisson.apitest.domain.repository.ICustomerRepository
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val api: ITestApi
) : ICustomerRepository {

    override suspend fun getAllCustomers(): List<CustomerResponse> {
        return api.getAllCustomers().body() ?: listOf()
    }

    override suspend fun deleteCustomer(id: String): String {
        return api.deleteCustomer(id).body() ?: ""
    }

    override suspend fun createCustomer(customer: CustomerBody): CustomerResponse? {
        return api.postCustomer(customer).body()
    }
}