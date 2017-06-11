package com.example.david.beercounter

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.orm.SugarRecord

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var total: TextView? = null
    private var today: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val current = this
        total = findViewById(R.id.totalText) as TextView
        today = findViewById(R.id.todayText) as TextView

        val fab = findViewById(R.id.addButton) as FloatingActionButton

        setCount()

        fab.setOnClickListener { view ->
            val drink = Drink()
            drink.save()
            setCount()
            Toast.makeText(current, "Adding beer", Toast.LENGTH_SHORT).show()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val context = this
        when (item.itemId) {
            R.id.nav_tools -> {
                startActivity(Intent(context, ToolsActivity::class.java))
            }
            R.id.nav_settings -> {

            }
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setCount() {
        var totalDrinks = SugarRecord.listAll(Drink::class.java)
        var todayDrinks = Drink.getToday()

        today?.text = todayDrinks.size.toString()
        total?.text = totalDrinks.size.toString()
    }
}
