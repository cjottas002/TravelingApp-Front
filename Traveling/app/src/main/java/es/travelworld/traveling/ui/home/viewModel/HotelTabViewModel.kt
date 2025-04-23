package es.travelworld.traveling.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.travelworld.traveling.data.repository.HotelRepository
import es.travelworld.traveling.data.remote.hotelmodel.Hotel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelTabViewModel @Inject constructor(private val repo: HotelRepository) : ViewModel() {

    private val _hotels = MutableLiveData<List<Hotel>>()
    val hotels: LiveData<List<Hotel>> = _hotels

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadHotels() {
        viewModelScope.launch {
            repo.fetchHotels().fold(
                onSuccess = { list ->
                    _hotels.value = list
                    _error.value = null
                },
                onFailure = { ex ->
                    _hotels.value = emptyList()
                    _error.value = ex.message
                }
            )
        }
    }
}