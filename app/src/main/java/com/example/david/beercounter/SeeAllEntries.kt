package com.example.david.beercounter

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ListViewCompat
import android.support.v7.widget.Toolbar
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import com.orm.SugarRecord

class SeeAllEntries : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_entries)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val entriesList = findViewById(R.id.entryList) as ListView
        var entries = SugarRecord.listAll(Drink::class.java)
        var entryLineItems = entries.map { d -> d.toLineItem() }
                .toTypedArray()

        var adapter = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_list_item_1,
            entryLineItems
        )

        entriesList.adapter = adapter
    }

}
