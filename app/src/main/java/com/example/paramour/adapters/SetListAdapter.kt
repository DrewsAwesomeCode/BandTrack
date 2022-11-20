package com.example.bandtrack.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bandtrack.R

class SetListAdapter(private val context: Activity,
                     private val id: Array<String>,
                     private val name: Array<String>,
                     private val date: Array<String>,
                     private val hotel: Array<String>,
                     private val city: Array<String>,
                     private val hours: Array<String>,
                     private val gifts: Array<String>,
                     private val events: Array<String>,
                     private val consideration: Array<String>,
                     private val giftConsideration: Array<String>
                      ): ArrayAdapter<String>(context, R.layout.set_list, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.set_list, null, true)

        val idText = rowView.findViewById(R.id.textViewSetId) as TextView
        val nameText = rowView.findViewById(R.id.textViewBandName) as TextView
        val dateText = rowView.findViewById(R.id.textViewDate) as TextView
        val hotelText = rowView.findViewById(R.id.textViewHotel) as TextView
        val cityText = rowView.findViewById(R.id.textViewCity) as TextView
        val hoursText = rowView.findViewById(R.id.textViewHours) as TextView
        val giftsText = rowView.findViewById(R.id.textViewGifts) as TextView
        val eventsText = rowView.findViewById(R.id.textViewEvents) as TextView
        val considerationText = rowView.findViewById(R.id.textViewConsideration) as TextView
        val giftConsiderationText = rowView.findViewById(R.id.textViewGiftConsideration) as TextView

        idText.text = "Id: ${id[position]}"
        nameText.text = "Name: ${name[position]}"
        dateText.text = "Date: ${date[position]}"
        hotelText.text = "Hotel: ${hotel[position]}"
        cityText.text = "City: ${city[position]}"
        hoursText.text = "Hours: ${hours[position]}"
        giftsText.text = "Gifts: ${gifts[position]}"
        eventsText.text = "Events: ${events[position]}"
        considerationText.text = "Consideration ${consideration[position]}"
        giftConsiderationText.text = "Gifts Consideration ${giftConsideration[position]}"

        return rowView
    }
}