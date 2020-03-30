package com.alexzh.mockdependenciesandroiduitests.screens.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexzh.mockdependenciesandroiduitests.R
import com.alexzh.mockdependenciesandroiduitests.data.CoffeeDrinksRepository
import com.alexzh.mockdependenciesandroiduitests.data.network.CoffeeDrinksServiceFactory
import com.alexzh.mockdependenciesandroiduitests.screens.common.UiState
import com.alexzh.mockdependenciesandroiduitests.screens.list.mapper.CoffeeDrinkMapper
import com.alexzh.mockdependenciesandroiduitests.screens.list.model.CoffeeDrinkUI
import kotlinx.android.synthetic.main.activity_main.*
import java.net.UnknownHostException

class CoffeeDrinksActivity : AppCompatActivity() {

    private val adapter: CoffeeDrinkAdapter by lazy {
        CoffeeDrinkAdapter()
    }

    private val viewModel: CoffeeDrinksViewModel by lazy {
        val coffeeDrinksService = CoffeeDrinksServiceFactory().create()
        val repository = CoffeeDrinksRepository(coffeeDrinksService)
        val mapper = CoffeeDrinkMapper()
        val factory = CoffeeDrinksViewModelFactory(repository, mapper)
        ViewModelProvider(this, factory).get(CoffeeDrinksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()

        viewModel.loadDrinks()
        viewModel.getCoffeeDrinks().observe(this, Observer {
            when (it) {
                is UiState.Success -> showCoffeeDrinks(it.data)
                is UiState.Error -> showErrorMessage(it.throwable)
            }
        })
    }

    private fun showCoffeeDrinks(coffeeDrinks: List<CoffeeDrinkUI>) {
        adapter.submitCoffeeDrinks(coffeeDrinks)
        errorMessageGroup.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showErrorMessage(throwable: Throwable) {
        errorMessage.setText(
            if (throwable is UnknownHostException) {
                R.string.error_network_message
            } else {
                R.string.error_unknown_message
            }
        )
        errorMessageGroup.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun setupUI() {
        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        )
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        tryAgain.setOnClickListener {
            viewModel.loadDrinks()
        }
    }
}
