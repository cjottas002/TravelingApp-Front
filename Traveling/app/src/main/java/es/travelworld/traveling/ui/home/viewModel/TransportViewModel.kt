package es.travelworld.traveling.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.travelworld.traveling.R
import es.travelworld.traveling.data.mapper.toTransport
import es.travelworld.traveling.data.remote.Transport
import es.travelworld.traveling.data.repository.TransportRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransportViewModel @Inject constructor(private val transportRepository: TransportRepository) : ViewModel() {
    private val _transports = MutableLiveData<List<Transport>>()
    val transports: LiveData<List<Transport>> get() = _transports

    init {
        viewModelScope.launch {
            transportRepository
                .getTransports()
                .map { entities ->
                    entities.map { it.toTransport() }
                }
                .collect { listOfTransport ->
                    _transports.value = listOfTransport
                }
        }
    }
}



