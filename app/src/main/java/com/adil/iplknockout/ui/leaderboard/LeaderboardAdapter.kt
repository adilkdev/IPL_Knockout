package com.adil.iplknockout.ui.leaderboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adil.iplknockout.R
import com.adil.iplknockout.data.models.TeamRank
import com.adil.iplknockout.databinding.ItemSingleLeaderboardBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class LeaderboardAdapter @Inject constructor() :
    RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    private var leaderboardList: List<TeamRank> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setupData(leaderboardList: List<TeamRank>) {
        this.leaderboardList = leaderboardList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        return LeaderboardViewHolder(
            ItemSingleLeaderboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.bind(leaderboardList[holder.adapterPosition])
        if (holder.adapterPosition == 2)
            holder.hideDivider()
    }

    override fun getItemCount() = leaderboardList.size

    class LeaderboardViewHolder(private val binding: ItemSingleLeaderboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamRank: TeamRank) {
            when (teamRank.rank.ordinal) {
                0 -> Picasso.get().load(R.drawable.gold).fit().centerCrop()
                    .into(binding.imageViewRank)
                1 -> Picasso.get().load(R.drawable.silver).fit().centerCrop()
                    .into(binding.imageViewRank)
                else -> Picasso.get().load(R.drawable.bronze).fit().centerCrop()
                    .into(binding.imageViewRank)
            }
            Picasso.get().load(teamRank.team.uriImage).fit().centerCrop()
                .into(binding.imageViewTeam)
            binding.tvTeamName.text = teamRank.team.teamName
        }

        fun hideDivider() {
            binding.divider.visibility = View.GONE
        }
    }
}