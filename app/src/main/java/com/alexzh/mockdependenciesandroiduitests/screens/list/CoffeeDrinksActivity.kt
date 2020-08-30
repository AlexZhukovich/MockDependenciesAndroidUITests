package com.alexzh.mockdependenciesandroiduitests.screens.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexzh.mockdependenciesandroiduitests.R
import com.alexzh.mockdependenciesandroiduitests.screens.common.UiState
import com.alexzh.mockdependenciesandroiduitests.screens.list.exception.NoDataAvailableException
import com.alexzh.mockdependenciesandroiduitests.screens.list.model.CoffeeDrinkUI
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoffeeDrinksActivity : AppCompatActivity() {
    private val viewModel: CoffeeDrinksViewModel by viewModel()

    private val adapter: CoffeeDrinkAdapter by lazy {
        CoffeeDrinkAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()

        viewModel.loadDrinks()
        viewModel.getCoffeeDrinks().observe(this, {
            when (it) {
                is UiState.Loading -> showLoadingProcess()
                is UiState.Success -> showCoffeeDrinks(it.data)
                is UiState.Error -> showErrorMessage(it.throwable)
            }
        })
    }

    private fun showLoadingProcess() {
        errorMessageGroup.visibility = View.GONE
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun showCoffeeDrinks(coffeeDrinks: List<CoffeeDrinkUI>) {
        adapter.submitCoffeeDrinks(coffeeDrinks)
        progressBar.visibility = View.GONE
        errorMessageGroup.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showErrorMessage(throwable: Throwable) {
        errorMessage.setText(
            when (throwable) {
                is NoDataAvailableException -> R.string.error_network_message
                else -> R.string.error_unknown_message
            }
        )
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        errorMessageGroup.visibility = View.VISIBLE
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
