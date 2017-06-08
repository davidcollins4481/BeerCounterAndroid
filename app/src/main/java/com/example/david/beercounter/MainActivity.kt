package com.example.david.beercounter

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var addBeerButton: FloatingActionButton? = null
    private var helper: BeerDBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBeerButton = findViewById(R.id.addbeer) as FloatingActionButton

        val current = this
        helper = BeerDBHelper(this)
        val db: SQLiteDatabase = helper?.writableDatabase as SQLiteDatabase

        addBeerButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(current, "Adding beer", Toast.LENGTH_SHORT).show()
                
                //db.addDrink()
            }
        })

        addBeerButton?.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                Toast.makeText(current, "Adding beer long click", Toast.LENGTH_SHORT).show()
                //db.addDrink()
                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                val i = Intent(applicationContext, SettingsActivity::class.java)
                startActivity(i)
                return true
            }
            else ->
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item)
        }
    }
}
