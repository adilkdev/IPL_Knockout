package com.adil.iplknockout.ui.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.adil.iplknockout.R
import com.adil.iplknockout.databinding.FragmentTeamsBinding
import com.adil.iplknockout.ui.main_activity.MainViewModel
import com.adil.iplknockout.utils.GridItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Adil Khan on 18/03/2022
 */

@AndroidEntryPoint
class TeamFragment : Fragment(R.layout.fragment_teams) {

    companion object {
        private const val GRID_ITEM_SPACING = 40
    }

    private lateinit var binding: FragmentTeamsBinding

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var teamsAdapter: TeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupClickListener()
    }

    private fun setupView() {
        with(binding.recyclerView) {
            teamsAdapter.setupTeamList(viewModel.getTeamList())
            adapter = teamsAdapter
            addItemDecoration(GridItemDecorator(GRID_ITEM_SPACING))
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun setupClickListener() {
        binding.btnStart.setOnClickListener {
            val action = TeamFragmentDirections.actionTeamFragmentToMatchFragment()
            binding.root.findNavController().navigate(action)
        }
    }

}