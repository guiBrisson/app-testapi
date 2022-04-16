package me.brisson.apitest.data

import me.brisson.apitest.data.model.CustomerBody
import me.brisson.apitest.data.model.CustomerResponse
import retrofit2.Response
import retrofit2.http.*


interface ITestApi {
    @GET("/customer")
    suspend fun getAllCustomers() : Response<List<CustomerResponse>>

    @DELETE("/customer/{id}")
    suspend fun deleteCustomer(
        @Path("id") id: String
    ) : Response<String>

    @POST("/customer")
    suspend fun postCustomer(@Body body: CustomerBody) : Response<CustomerResponse>

}
