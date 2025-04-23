package es.travelworld.traveling.ui.home.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.travelworld.traveling.R
import es.travelworld.traveling.data.remote.hotelmodel.Hotel
import es.travelworld.traveling.data.remote.hotelmodel.HotelResponse


class HotelAdapter(hotelResponse: HotelResponse) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {
    private val hotels: List<Hotel> = hotelResponse.results

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotels[position]

        val address = hotel.address.streetAddress
        val locality = hotel.address.locality
        val currentPrice = hotel.ratePlan.price.current + " €"
        val rating = "Rating: " + hotel.guestReviews.rating

        holder.addressTextView.text = address
        holder.cityTextView.text = locality
        holder.priceTextView.text = currentPrice
        holder.ratingTextView.text = rating

        Picasso.get().load(hotel.optimizedThumbUrls.srpDesktop).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_hotel_image)
        val addressTextView: TextView = itemView.findViewById(R.id.tv_hotel_address)
        val cityTextView: TextView = itemView.findViewById(R.id.tv_hotel_city)
        val priceTextView: TextView = itemView.findViewById(R.id.tv_hotel_price)
        val ratingTextView: TextView = itemView.findViewById(R.id.tv_hotel_rating)
    }
}
