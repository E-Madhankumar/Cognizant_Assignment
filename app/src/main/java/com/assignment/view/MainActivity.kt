package com.assignment.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.di.factory.ViewModelProviderFactory
import com.assignment.model.RowsItem
import com.assignment.util.ImagePreloader
import com.assignment.view.adapter.FactsAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.ListPreloader.PreloadSizeProvider
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.FixedPreloadSizeProvider
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelProveder: ViewModelProviderFactory
    lateinit var viewModel: MainViewModel
    var adapter: FactsAdapter? = null
    var items:MutableList<RowsItem> = mutableListOf()

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
            adapter?.clear()
            swipeContainer?.isRefreshing = false
            adapter?.update(it.filter { it.title!=null } as MutableList<RowsItem>)
        })

        viewModel.getErrorObserable().observe(this, Observer {
            Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView(){
        adapter = FactsAdapter(this,mutableListOf())
        rc_facts?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        rc_facts?.setHasFixedSize(true)
        rc_facts?.adapter = adapter
        rc_facts?.setItemViewCacheSize(10)
        rc_facts?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        getFacts()
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
        swipeContainer?.setOnRefreshListener {
            getFacts()
        }
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
