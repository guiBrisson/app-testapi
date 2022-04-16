package me.brisson.apitest.domain.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.brisson.apitest.data.model.CustomerResponse
import me.brisson.apitest.domain.common.Resource
import me.brisson.apitest.domain.repository.ICustomerRepository
import java.io.IOException
import javax.inject.Inject

class AllCustomersUseCase @Inject constructor(
    private val repository: ICustomerRepository
) {
    operator fun invoke() : Flow<Resource<List<CustomerResponse>>> = flow {
        try {
            emit(Resource.Loading())
            val customers = repository.getAllCustomers()
            emit(Resource.Success(customers))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message ?: "Could not reach server. Check your internet connection"))
            Log.e(TAG, "invoke: ${e.message}", )
        }
    }

    companion object {
        private const val TAG = "AllCustomersUseCase"
    }
}