package com.example.bandtrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bandtrack.R
import com.example.bandtrack.data.Band

class BandAdapter(
    private val dataset: List<Band>
): RecyclerView.Adapter<BandAdapter.BandViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class BandViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val bandNameTextView: TextView = view.findViewById(R.id.band_bandname)
        val timesSeenTextView: TextView = view.findViewById(R.id.band_timesSeen)
        val averageRatingTextView: TextView = view.findViewById(R.id.band_averageScore)

    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_band, parent, false)

        return BandViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        val band = dataset[position]

        holder.bandNameTextView.text = band.bandName
        holder.timesSeenTextView.text = band.timesSeen.toString()
        holder.averageRatingTextView.text = band.averageScore.toString()
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size
}