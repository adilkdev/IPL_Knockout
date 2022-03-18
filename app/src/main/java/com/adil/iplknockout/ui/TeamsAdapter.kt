package com.adil.iplknockout.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adil.iplknockout.data.models.Team
import com.adil.iplknockout.databinding.ItemSingleTeamBinding
import com.squareup.picasso.Picasso

class TeamsAdapter(private val teamList: List<Team>)
    : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamViewHolder(
            ItemSingleTeamBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        )

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList[holder.adapterPosition])
    }

    override fun getItemCount() = teamList.size

    class TeamViewHolder(private val binding: ItemSingleTeamBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(team: Team) {
            Picasso.get().load(team.uriImage)
                .fit()
                .centerCrop()
                .into(binding.imageView)
        }

    }
}