package com.example.david.beercounter

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.content.Intent
import android.widget.ListView

class ToolsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val toolsList = findViewById(R.id.toolsList) as ListView
        val tools = createToolsList()

        val adapter = ArrayAdapter<Tool>(
                applicationContext,
                android.R.layout.simple_list_item_1,
                tools)

        toolsList.adapter = adapter

        val context = this

        toolsList.setOnItemClickListener(object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val tool = tools.get(position)
                when (tool.name) {
                    "see_all_entries" -> startActivity(Intent(context, SeeAllEntries::class.java))
                }
            }
        })
    }

    private fun createToolsList() : ArrayList<Tool> {
        var tools = arrayListOf<Tool>()
        tools.add(Tool(name="see_all_entries", label="See All Entries"))
        return tools
    }

}

data class Tool(val name: String, val label: String) {
    override fun toString(): String {
        return this.label
    }
}