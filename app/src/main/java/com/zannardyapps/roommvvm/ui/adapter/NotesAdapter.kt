package com.zannardyapps.roommvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zannardyapps.roommvvm.database.model.Notes
import com.zannardyapps.roommvvm.databinding.RecyclerviewNotesItemBinding

class NotesAdapter: ListAdapter<Notes, NotesAdapter.NotesViewHolder>(NotesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = RecyclerviewNotesItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.notesTitle, current.notesDescription)
    }


    class NotesViewHolder(binding: RecyclerviewNotesItemBinding):
        RecyclerView.ViewHolder(binding.root){

        private val notesTitle = binding.textViewTitle
        private val notesDescription = binding.textViewDescription

        fun bind(title: String?, description: String?){
            notesTitle.text = title
            notesDescription.text = description
        }
    }

    class NotesComparator: DiffUtil.ItemCallback<Notes>(){
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.notesTitle == newItem.notesTitle
        }

    }
}