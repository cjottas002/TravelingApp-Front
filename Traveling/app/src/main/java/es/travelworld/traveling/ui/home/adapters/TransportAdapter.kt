package es.travelworld.traveling.ui.home.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.travelworld.traveling.databinding.ItemTransportBinding
import es.travelworld.traveling.data.remote.Transport

class TransportAdapter(private val onClick: (Transport) -> Unit) : ListAdapter<Transport, TransportAdapter.TransportViewHolder>(TransportDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val binding = ItemTransportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        val transport = getItem(position)
        holder.bind(transport)
    }

    inner class TransportViewHolder(private val binding: ItemTransportBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var transport: Transport

        init {
            binding.root.setOnClickListener {
                onClick(transport)
            }
        }

        fun bind(transport: Transport) {
            this.transport = transport
            binding.imageTransport.setImageResource(transport.imageRes)
            binding.textTransport.text = transport.name
            binding.priceTransport.text = transport.price
        }

    }

    class TransportDiffCallback : DiffUtil.ItemCallback<Transport>() {
        override fun areItemsTheSame(oldItem: Transport, newItem: Transport): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Transport, newItem: Transport): Boolean {
            return oldItem == newItem
        }
    }
}
