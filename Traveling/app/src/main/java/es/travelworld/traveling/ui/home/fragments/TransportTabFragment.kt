package es.travelworld.traveling.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import es.travelworld.traveling.databinding.FragmentTabTransportBinding
import es.travelworld.traveling.ui.home.adapters.TransportAdapter
import es.travelworld.traveling.ui.home.viewModel.TransportViewModel

@AndroidEntryPoint
class TransportTabFragment : Fragment() {

    private var _binding: FragmentTabTransportBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TransportViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabTransportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TransportAdapter { transport ->
            Toast.makeText(requireContext(), "Selected: ${transport.name}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.isNestedScrollingEnabled = true

        viewModel.transports.observe(viewLifecycleOwner) { list ->
            list?.let { adapter.submitList(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}