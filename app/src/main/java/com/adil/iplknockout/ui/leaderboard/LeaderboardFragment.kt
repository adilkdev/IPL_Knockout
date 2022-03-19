package com.adil.iplknockout.ui.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adil.iplknockout.databinding.FragmentLeaderboardBinding
import com.adil.iplknockout.ui.main_activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LeaderboardFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentLeaderboardBinding

    @Inject
    lateinit var leaderboardAdapter: LeaderboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeaderboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnRestart.setOnClickListener {
            viewModel.restart()
            val action = LeaderboardFragmentDirections.actionLeaderBoardFragmentToTeamFragment()
            view?.findNavController()?.navigate(action)
        }
    }

    private fun setupView() {
        val rankList = viewModel.getTeamRankList()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            leaderboardAdapter.setupData(rankList)
            adapter = leaderboardAdapter
        }
    }

}