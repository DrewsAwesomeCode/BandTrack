package com.example.bandtrack.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bandtrack.R
import com.example.bandtrack.data.Set

class SetRecAdapter(
                     private val dataset: List<Set>
): RecyclerView.Adapter<SetRecAdapter.SetViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class SetViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val bandNameTextView: TextView = view.findViewById(R.id.set_bandname)
        val dateTextView: TextView = view.findViewById(R.id.set_date)
        val venueTextView: TextView = view.findViewById(R.id.set_venue)
        val cityTextView: TextView = view.findViewById(R.id.set_city)
        val spotTextView: TextView = view.findViewById(R.id.set_spot)
        val ratingTextView: TextView = view.findViewById(R.id.set_rating)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_set, parent, false)

        return SetViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        val set = dataset[position]
        val niceDate = (
                mapMonths(set.date.toString().substring(5,7)) + " " +
                set.date.toString().substring(8,10) + ", " +
                set.date.toString().substring(0,4))
        holder.bandNameTextView.text = set.bandName
        holder.dateTextView.text = niceDate
        holder.venueTextView.text = set.venue
        holder.cityTextView.text = set.city
        holder.spotTextView.text = set.spot
        holder.ratingTextView.text = set.rating.toString() + "/100"
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size

    fun mapMonths(str: String): String {
        if (str == "01") {return "Jan"}
        else if (str == "02") {return "Feb"}
        else if (str == "03") {return "Mar"}
        else if (str == "04") {return "Apr"}
        else if (str == "05") {return "May"}
        else if (str == "06") {return "Jun"}
        else if (str == "07") {return "Jul"}
        else if (str == "08") {return "Aug"}
        else if (str == "09") {return "Sep"}
        else if (str == "10") {return "Oct"}
        else if (str == "11") {return "Nov"}
        else if (str == "12") {return "Dec"}
        else return "FUCK"
    }
}