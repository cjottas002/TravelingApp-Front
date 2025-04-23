package es.travelworld.traveling.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import es.travelworld.traveling.data.remote.hotelmodel.HotelResponse
import es.travelworld.traveling.databinding.FragmentTabHotelBinding
import es.travelworld.traveling.ui.home.adapters.HotelAdapter
import es.travelworld.traveling.ui.home.viewModel.HotelTabViewModel
import kotlin.getValue

@AndroidEntryPoint
class HotelTabFragment : Fragment() {

    private var _binding: FragmentTabHotelBinding? = null
    private val binding get() = _binding!!
    private val hotelVM: HotelTabViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchData() {
        binding.rvHotels.layoutManager = LinearLayoutManager(requireContext())

        hotelVM.hotels.observe(viewLifecycleOwner) { list ->
            binding.rvHotels.adapter = HotelAdapter(
                HotelResponse(totalCount = list.size, results = list, pagination = null)
            )

            binding.rvHotels.visibility = if (list.isEmpty()) View.GONE else View.VISIBLE
        }

        hotelVM.error.observe(viewLifecycleOwner) { msg ->
            msg?.let {
                Snackbar.make(binding.rvHotels, it, Snackbar.LENGTH_LONG).show()
            }
        }

        hotelVM.loadHotels()
    }
}