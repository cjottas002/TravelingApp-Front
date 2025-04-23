package es.travelworld.traveling.ui.onboarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import es.travelworld.traveling.R
import es.travelworld.traveling.databinding.FragmentOnboarding2Binding

class Onboarding2Fragment : Fragment() {

    private var _binding: FragmentOnboarding2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOnboarding2Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSkip.setOnClickListener {
            val act = Onboarding2FragmentDirections.navOnboar2ToLogin("", "")
            findNavController().navigate(act)
        }

        binding.bNext.setOnClickListener {
            findNavController().navigate(R.id.nav_onboar2_to_onboar3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}