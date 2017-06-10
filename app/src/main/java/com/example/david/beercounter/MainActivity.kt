package com.example.david.beercounter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.orm.SugarRecord

class MainActivity : AppCompatActivity() {
    private var addBeerButton: FloatingActionButton? = null
    private var total: TextView? = null
    private var today: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBeerButton = findViewById(R.id.addbeer) as FloatingActionButton
        today = findViewById(R.id.todayText) as TextView
        total = findViewById(R.id.totalText) as TextView

        setCount()

        val current = this

        addBeerButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var drink = Drink()
                drink.save()

                setCount()

                Toast.makeText(current, "Adding beer", Toast.LENGTH_SHORT).show()
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

    private fun setCount() {
        var totalDrinks = SugarRecord.listAll(Drink::class.java)
        var todayDrinks = Drink.getToday()

        today?.text = todayDrinks.size.toString()
        total?.text = totalDrinks.size.toString()
    }
}
