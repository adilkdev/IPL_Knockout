package com.adil.iplknockout.ui.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adil.iplknockout.data.models.TeamPair
import com.adil.iplknockout.databinding.ItemSingleMatchBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * Created by Adil Khan on 18/03/2022
 */

class MatchAdapter @Inject constructor() : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    private var list: List<TeamPair> = arrayListOf()

    fun setupData(list: List<TeamPair>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MatchViewHolder(
            ItemSingleMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(list[holder.adapterPosition])
    }

    override fun getItemCount() = list.size

    class MatchViewHolder(private val binding: ItemSingleMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(teamPair: TeamPair) {
            Picasso.get().load(teamPair.firstTeam.uriImage)
                .fit()
                .centerCrop()
                .into(binding.imageViewFirst)
            Picasso.get().load(teamPair.secondTeam.uriImage)
                .fit()
                .centerCrop()
                .into(binding.imageViewSecond)
        }

    }
}