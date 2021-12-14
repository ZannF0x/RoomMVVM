package com.zannardyapps.roommvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zannardyapps.roommvvm.database.model.Notes
import com.zannardyapps.roommvvm.databinding.RecyclerviewNotesItemBinding

class NotesAdapter: ListAdapter<Notes, NotesAdapter.NotesViewHolder>(NotesComparator()) {

    var listenerActionRemove:(Notes) -> Unit = {}
    var listenerActionEdit:(Notes) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = RecyclerviewNotesItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, current.notesTitle, current.notesDescription)
    }

    inner class NotesViewHolder(binding: RecyclerviewNotesItemBinding):
        RecyclerView.ViewHolder(binding.root){
        private val notesDelete = binding.notesOptions
        private val notesEdit = binding.notesOptionsEdit
        private val notesTitle = binding.textViewTitle
        private val notesDescription = binding.textViewDescription

        fun bind(item: Notes, title: String?, description: String?){
            notesTitle.text = title
            notesDescription.text = description
            notesDelete.setOnClickListener {
                listenerActionRemove(item)
            }
            notesEdit.setOnClickListener {
                listenerActionEdit(item)
            }
        }
    }

    class NotesComparator: DiffUtil.ItemCallback<Notes>(){
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.notesId == newItem.notesId
        }
    }
}
