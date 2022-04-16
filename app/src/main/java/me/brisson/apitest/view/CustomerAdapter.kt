package me.brisson.apitest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.brisson.apitest.data.model.CustomerResponse
import me.brisson.apitest.databinding.ItemCustomerBinding

class CustomerAdapter(
    private val customerResponses : List<CustomerResponse> = listOf(),
    private val onDeleteClick: (item: CustomerResponse) -> Unit
) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {


    inner class ViewHolder(
        private val binding: ItemCustomerBinding,
        private val onDeleteClick: (item: CustomerResponse) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CustomerResponse) {
            with (binding) {
                "${item.firstName} ${item.lastName}".also { textFullNameValue.text = it }
                textEmailValue.text = item.email

                imageDelete.setOnClickListener {
                    onDeleteClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCustomerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onDeleteClick
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(customerResponses[position])
    }

    override fun getItemCount(): Int = customerResponses.size
}