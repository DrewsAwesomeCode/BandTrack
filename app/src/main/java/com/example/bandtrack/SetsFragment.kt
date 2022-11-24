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
import android.content.Context
import android.content.Intent
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_sets.*

class SetsFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_sets, container, false)

        val databaseHandler: DatabaseHandler = DatabaseHandler(context, null)
        val data = databaseHandler.viewSet()

        view.recycler_view.layoutManager = LinearLayoutManager(activity)
        view.recycler_view.adapter = SetRecAdapter(data)

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