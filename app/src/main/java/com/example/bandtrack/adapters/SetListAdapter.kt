package com.example.bandtrack.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bandtrack.R

class SetListAdapter(private val context: Activity,
                     private val setId: Array<String>,
                     private val bandName: Array<String>,
                     private val date: Array<String>,
                     private val spot: Array<String>,
                     private val rating: Array<String>,
                     private val venue: Array<String>,
                     private val city: Array<String>,
                     private val ticketCost: Array<String>,

                      ): ArrayAdapter<String>(context, R.layout.set_list, bandName) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.set_list, null, true)

        val idText = rowView.findViewById(R.id.textViewSetId) as TextView
        val nameText = rowView.findViewById(R.id.textViewBandName) as TextView
        val dateText = rowView.findViewById(R.id.textViewDate) as TextView
        val spotText = rowView.findViewById(R.id.textViewSpot) as TextView
        val ratingText = rowView.findViewById(R.id.textViewRating) as TextView
        val venueText = rowView.findViewById(R.id.textViewVenue) as TextView
        val cityText = rowView.findViewById(R.id.textViewCity) as TextView
        val ticketCostText = rowView.findViewById(R.id.textViewTicketCost) as TextView

        idText.text = "Id: ${setId[position]}"
        nameText.text = "Name: ${bandName[position]}"
        dateText.text = "Date: ${date[position]}"
        spotText.text = "Spot: ${spot[position]}"
        ratingText.text = "Rating: ${rating[position]}"
        venueText.text = "Venue: ${venue[position]}"
        cityText.text = "City: ${city[position]}"
        ticketCostText.text = "Ticket Cost: ${ticketCost[position]}"

        return rowView
    }
}