package com.adil.iplknockout.ui.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adil.iplknockout.databinding.FragmentTeamsBinding
import com.adil.iplknockout.ui.main_activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Adil Khan on 18/03/2022
 */

@AndroidEntryPoint
class MatchFragment : Fragment() {

    companion object {
        const val TEXT_SIMULATE = "SIMULATE"
        const val TEXT_MATCHES = "MATCHES"
    }

    private lateinit var binding: FragmentTeamsBinding

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var matchAdapter: MatchAdapter

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
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.teamPairListLiveData.observe(viewLifecycleOwner) {
            matchAdapter.setupData(it)
        }
        viewModel.isGameOver.observe(viewLifecycleOwner) {
            val action = MatchFragmentDirections.actionMatchFragmentToLeaderBoardFragment()
            binding.root.findNavController().navigate(action)
        }
    }

    private fun setupClickListener() {
        binding.btnStart.setOnClickListener {
            viewModel.run {
                when {
                    getTeamPairs().size > 2 -> getWinnersOfMatch()
                    getTeamPairs().size == 2 -> getWinnersAndLosersOfMatch(isForThirdPos = true)
                    getTeamPairs().size == 1 -> getWinnersAndLosersOfMatch(isForThirdPos = false)
                }
            }
        }
    }

    private fun setupView() {
        binding.btnStart.text = TEXT_SIMULATE
        binding.tvSubtitle.text = TEXT_MATCHES
        binding.recyclerView.apply {
            adapter = matchAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

}