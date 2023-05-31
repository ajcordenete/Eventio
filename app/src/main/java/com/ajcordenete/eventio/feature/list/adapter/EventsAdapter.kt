package com.ajcordenete.eventio.feature.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ajcordenete.core.base.BaseListAdapter
import com.ajcordenete.domain.models.Event
import com.ajcordenete.eventio.R
import com.ajcordenete.eventio.databinding.ItemEventBinding
import com.ajcordenete.eventio.utils.DateTimeUtils

class EventsAdapter: BaseListAdapter<Event, EventsAdapter.EventViewHolder>(EventDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun createViewHolder(parent: ViewGroup): EventViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_event, parent, false)

        val binding = ItemEventBinding.bind(view)
        return EventViewHolder(binding)
    }

    inner class EventViewHolder(override val binding: ItemEventBinding) : BaseViewViewHolder<Event>(binding) {

        override fun bind(item: Event) {
            super.bind(item)

            binding.txtTimeStamp.text = DateTimeUtils.getDateTimeString(item.timestampMillis)
        }
    }
}