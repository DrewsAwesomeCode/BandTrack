package com.example.bandtrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bandtrack.adapters.SetRecAdapter
import com.example.bandtrack.data.DatabaseHandler
import kotlinx.android.synthetic.main.fragment_sets.view.*
import android.content.Intent
import com.example.bandtrack.adapters.BandAdapter
import com.example.bandtrack.data.Band
import kotlinx.android.synthetic.main.fragment_sets.*

class BandsFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_bands, container, false)

        val databaseHandler: DatabaseHandler = DatabaseHandler(context, null)
        val setData = databaseHandler.viewSet()

        val bandData = setData.groupBy { it.bandName }
            .map{
                set ->
                val getTimesSeen = set.value.count()
                val averageRating = (set.value.sumOf { it.rating } / getTimesSeen.toDouble() )

                Band(
                    bandName = set.key,
                    timesSeen = getTimesSeen,
                    averageScore = averageRating
                )
            }


        view.recycler_view.layoutManager = LinearLayoutManager(activity)
        view.recycler_view.adapter = BandAdapter(bandData)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            activity?.let {
                val intent = Intent(it,AddSetActivity::class.java)
                it.startActivity(intent)
            }
        }
    }
}