package com.example.bandtrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.bandtrack.adapters.SetRecAdapter
import com.example.bandtrack.data.DatabaseHandler
import com.example.bandtrack.data.Set
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val intent = Intent(this,AddSetActivity::class.java)
            startActivity(intent)
        }

        // Initialize data.
        val db = DatabaseHandler(this, null)
        val myDataset: List<Set> = db.viewSet()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = SetRecAdapter(this, myDataset)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }
}