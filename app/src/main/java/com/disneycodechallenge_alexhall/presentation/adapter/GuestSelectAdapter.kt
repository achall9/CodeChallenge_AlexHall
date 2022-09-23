package com.disneycodechallenge_alexhall.presentation.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.disneycodechallenge_alexhall.custom.StickyAdapter
import com.disneycodechallenge_alexhall.data.model.GuestHeaderModel
import com.disneycodechallenge_alexhall.data.model.GuestModel
import com.disneycodechallenge_alexhall.data.model.InfoModel
import com.disneycodechallenge_alexhall.data.model.Section
import com.disneycodechallenge_alexhall.databinding.ItemGuestSelectBinding
import com.disneycodechallenge_alexhall.databinding.ItemHeaderBinding
import com.disneycodechallenge_alexhall.databinding.ItemInfoBinding

class GuestSelectAdapter(
    val activity: Activity,
    var guestList: List<Section>,
    var onSelect: () -> Unit
) :
    StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Section.header -> {
                return HeaderViewHolder(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            Section.item -> {
                return GuestViewHolder(
                    ItemGuestSelectBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return InfoViewHolder(
                    ItemInfoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == Section.header) {
            (holder as HeaderViewHolder).bind(guestList[position] as GuestHeaderModel)
        } else if (getItemViewType(position) == Section.item) {
            (holder as GuestViewHolder).bind(position, guestList[position] as GuestModel)
        } else {
            (holder as InfoViewHolder).bind(guestList[position] as InfoModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return guestList[position].type()
    }

    override fun getItemCount(): Int {
        return guestList.size
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        return guestList[itemPosition].sectionPosition()
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?, headerPosition: Int) {
        val model = guestList[headerPosition] as GuestHeaderModel
        Log.d("TAG", "onBindHeaderViewHolder: ====> ${model.name}")
        (holder as HeaderViewHolder).bind(model)
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return createViewHolder(parent!!, Section.header)
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: GuestHeaderModel) {
            binding.txtTitle.text = header.name
        }
    }

    inner class InfoViewHolder(private val binding: ItemInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: InfoModel) {
            binding.info.text = header.name
        }
    }

    inner class GuestViewHolder(private val binding: ItemGuestSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, guest: GuestModel) {
            binding.guestCheck.text = guest.name
            binding.guestCheck.isChecked = guest.isSelect
            binding.guestCheck.setOnCheckedChangeListener { _, isChecked ->
                guest.isSelect = isChecked
                (guestList[position] as GuestModel).isSelect = isChecked
                onSelect()
            }
        }

    }
}