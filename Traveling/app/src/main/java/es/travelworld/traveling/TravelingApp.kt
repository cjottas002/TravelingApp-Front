package es.travelworld.traveling

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import es.travelworld.traveling.data.local.AppDatabase
import es.travelworld.traveling.data.local.entities.TransportEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class TravelingApp : Application() {

    @Inject lateinit var database : AppDatabase

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {

            val dao = database.transportDao()
            if(!dao.getAllTransportsOnce().isEmpty()) return@launch

            val initial = listOf(
                TransportEntity(name = "AirPlane",     imageRes = R.drawable.pestania1_airplain,     price = "$11/day"),
                TransportEntity(name = "Bus",          imageRes = R.drawable.pestania1_bus,          price = "$14/day"),
                TransportEntity(name = "Classic Car",  imageRes = R.drawable.pestania1_classiccar,   price = "$34/day"),
                TransportEntity(name = "Electric Car", imageRes = R.drawable.pestania1_electriccar,  price = "$45/day"),
                TransportEntity(name = "Flying Car",   imageRes = R.drawable.pestania1_flyingcar,    price = "$500/day"),
                TransportEntity(name = "MotorHome",    imageRes = R.drawable.pestania1_motorhome,    price = "$23/day"),
                TransportEntity(name = "PickUp Car",   imageRes = R.drawable.pestania1_pickupcar,    price = "$10/day"),
                TransportEntity(name = "Sport Car",    imageRes = R.drawable.pestania1_sportcart,    price = "$55/day")
            )

            dao.insertAll(initial)
        }
    }
}