package me.brisson.apitest.domain.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.brisson.apitest.domain.common.Resource
import me.brisson.apitest.domain.repository.ICustomerRepository
import java.io.IOException
import javax.inject.Inject

class DeleteCustomerUseCase @Inject constructor(
    private val repository: ICustomerRepository
) {
    operator fun invoke(id: String) : Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.deleteCustomer(id)
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message ?: "Could not reach server. Check your internet connection"))
            Log.e(TAG, "invoke: ${e.message}", )
        }
    }

    companion object {
        private const val TAG = "DeleteCustomerUseCase"
    }
}