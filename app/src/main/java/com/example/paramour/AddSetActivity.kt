package com.example.bandtrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.DialogInterface
import android.app.AlertDialog
import android.content.Intent
import com.example.bandtrack.adapters.SetListAdapter
import com.example.bandtrack.data.DatabaseHandler
import com.example.bandtrack.data.Set
import kotlinx.android.synthetic.main.activity_add_set.*
import kotlinx.android.synthetic.main.activity_add_set.listView
import java.sql.Date


class AddSetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_set)

        buttonSetDone.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
    //method for saving records in database
    fun saveRecord(view: View){
        val id = s_id.text.toString()
        val name = s_name.text.toString()
        val date = s_date.text.toString()
        val rating = s_rating.text.toString()
        val venue = s_venue.text.toString()
        val city = s_city.text.toString()
        val ticketcost = s_ticketcost.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this,null)
        if(id.trim()!="" && name.trim()!="" && date.trim()!="" && rating.trim()!="" && venue.trim()!="" &&
            city.trim()!="" && ticketcost.trim()!="") {

            val status = databaseHandler.addSet(Set(Integer.parseInt(id),name, Date.valueOf(date), rating.toDouble(),
                                                    venue, city, ticketcost.toDouble()))
            if(status > -1){
                Toast.makeText(applicationContext,"Record saved!",Toast.LENGTH_LONG).show()
                s_id.text.clear()
                s_name.text.clear()
                s_date.text.clear()
                s_rating.text.clear()
                s_venue.text.clear()
                s_city.text.clear()
                s_ticketcost.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"Cannot be blank",Toast.LENGTH_LONG).show()
        }

    }
    //method for read records from database in ListView
    fun viewRecord(view: View){
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler= DatabaseHandler(this, null)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val set: List<Set> = databaseHandler.viewSet()
        val setArrayId = Array<String>(set.size){"0"}
        val setArrayName = Array<String>(set.size){"null"}
        val setArrayDate = Array<String>(set.size){"1900-01-01"}
        val setArrayRating = Array<String>(set.size){"0"}
        val setArrayVenue = Array<String>(set.size){"null"}
        val setArrayCity = Array<String>(set.size){"null"}
        val setArrayTicketCost = Array<String>(set.size){"0.0"}
        var index = 0
        for(s in set){
            setArrayId[index] = s.setId.toString()
            setArrayName[index] = s.bandName
            setArrayDate[index] = s.date.toString()
            setArrayRating[index] = s.rating.toString()
            setArrayVenue[index] = s.venue
            setArrayCity[index] = s.city
            setArrayTicketCost[index] = s.ticketCost.toString()
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = SetListAdapter(
            this,
            setArrayId,
            setArrayName,
            setArrayDate,
            setArrayRating,
            setArrayVenue,
            setArrayCity,
            setArrayTicketCost
        )
        listView.adapter = myListAdapter
    }
    //method for updating records based on user id
    fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.set_update_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateSetId) as EditText
        val edtName = dialogView.findViewById(R.id.updateBandName) as EditText
        val edtDate = dialogView.findViewById(R.id.updateDate) as EditText
        val edtRating = dialogView.findViewById(R.id.updateRating) as EditText
        val edtVenue = dialogView.findViewById(R.id.updateVenue) as EditText
        val edtCity = dialogView.findViewById(R.id.updateCity) as EditText
        val edtTicketCost = dialogView.findViewById(R.id.updateTicketCost) as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateName = edtName.text.toString()
            val updateDate = edtDate.text.toString()
            val updateRating = edtRating.text.toString()
            val updateVenue = edtVenue.text.toString()
            val updateCity = edtCity.text.toString()
            val updateTicketCost = edtTicketCost.text.toString()

            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler= DatabaseHandler(this, null)
            if(updateId.trim()!="" && updateName.trim()!="" && updateDate.trim()!="" && updateRating.trim()!=""
                && updateVenue.trim()!="" && updateCity.trim()!=""  && updateTicketCost.trim()!=""){
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler.updateSet(Set(
                    Integer.parseInt(updateId),
                    updateName,
                    Date.valueOf(updateDate),
                    updateRating.toDouble(),
                    updateVenue,
                    updateCity,
                    updateTicketCost.toDouble()
                ))

                if(status > -1){
                    Toast.makeText(applicationContext,"Record updated!",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"Cannot be blank",Toast.LENGTH_LONG).show()
            }
        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
    //method for deleting records based on id
    fun deleteRecord(view: View){
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.set_delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteSetId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter ID below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this,null)
            if(deleteId.trim()!=""){
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler.deleteSet(Set(
                    Integer.parseInt(deleteId),
                    "",
                    Date.valueOf("1900-01-01"),
                    0.0,
                    "",
                    "",
                    0.0,

                ))
                if(status > -1){
                    Toast.makeText(applicationContext,"Record Deleted!",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"Cannot be blank",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
}