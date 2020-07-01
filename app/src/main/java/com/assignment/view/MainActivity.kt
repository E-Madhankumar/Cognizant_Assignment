package com.assignment.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.di.factory.ViewModelProviderFactory
import com.assignment.view.adapter.FactsAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelProveder: ViewModelProviderFactory
    lateinit var viewModel: MainViewModel
    var adapter: FactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this,viewModelProveder).get(MainViewModel::class.java)
        initObservable()
        initRecyclerView()
        checkSavedState(savedInstanceState)
    }

    private fun initObservable(){
        viewModel.getPosterObservable().observe(this, Observer {
            adapter?.update(it)
        })

        viewModel.getErrorObserable().observe(this, Observer {
            Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView(){
        adapter = FactsAdapter(mutableListOf())
        rc_facts?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        rc_facts?.setHasFixedSize(true)
        rc_facts?.adapter = adapter
        getFacts()
    }
    private fun checkSavedState(savedInstanceState:Bundle?){
        if(savedInstanceState != null) {
            savedInstanceState.getString("rc_position")?.let {
                rc_facts?.layoutManager?.scrollToPosition(it.toInt())
            }
        }
    }

    fun getFacts(){
        viewModel.getFacts()
    }
}
