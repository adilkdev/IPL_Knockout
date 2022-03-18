package com.adil.iplknockout.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.adil.iplknockout.databinding.ActivityMainBinding
import com.adil.iplknockout.utils.GridItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val GRID_ITEM_SPACING = 40
    }

    private lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.setup()
        setupView()
    }

    private fun setupView() {
        with(binding.recyclerView) {
            adapter = TeamsAdapter(
                viewModel.getTeamList()
            )
            addItemDecoration(GridItemDecorator(GRID_ITEM_SPACING))
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }

}