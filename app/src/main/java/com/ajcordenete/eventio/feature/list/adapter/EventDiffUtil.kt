package com.ajcordenete.eventio.feature.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ajcordenete.domain.models.Event

class EventDiffUtil : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
        oldItem == newItem
}