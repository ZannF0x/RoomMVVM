package com.zannardyapps.roommvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zannardyapps.roommvvm.R
import com.zannardyapps.roommvvm.database.model.Notes
import com.zannardyapps.roommvvm.databinding.RecyclerviewNotesItemBinding

class NotesAdapter: ListAdapter<Notes, NotesAdapter.NotesViewHolder>(NotesComparator()) {

    var listenerActionRemove:(Notes) -> Unit = {

    }

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
        private val notesTitle = binding.textViewTitle
        private val notesDescription = binding.textViewDescription

        fun bind(item: Notes, title: String?, description: String?){
            notesTitle.text = title
            notesDescription.text = description

            notesDelete.setOnClickListener {
                listenerActionRemove(item)
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

/*
private fun showPopup(item: Notes){
            val icMore = notesDelete
            val popupMenu = PopupMenu(icMore.context, icMore)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->

                when(menuItem.itemId){
                    //R.id.actionEdit -> listenerActionEdit(item)
                    R.id.actionDelete -> listenerActionRemove(item)
                }

                return@setOnMenuItemClickListener true
            }

            popupMenu.show()
        }
 */