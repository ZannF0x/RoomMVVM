package com.zannardyapps.roommvvm.ui.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.zannardyapps.roommvvm.application.NotesApplication
import com.zannardyapps.roommvvm.databinding.ActivityDetailsBinding
import com.zannardyapps.roommvvm.ui.viewmodel.NotesViewModel
import com.zannardyapps.roommvvm.ui.viewmodel.NotesViewModelFactory

class EditNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val notesViewModel: NotesViewModel by viewModels{
        NotesViewModelFactory((application as NotesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notesToolbar()
        val oldTitle: String = intent.getStringExtra("title").toString()
        val oldDescription: String = intent.getStringExtra("description").toString()
        binding.editTitle.setText(oldTitle)
        binding.editDescription.setText(oldDescription)
    }

    override fun onResume() {
        super.onResume()
        val id = intent.getIntExtra("id", 0)
        binding.btnEditNote.setOnClickListener {
            val replyIntent = Intent()
            if (binding.editTitle.text.isEmpty()){
                binding.editTitle.error = "required title"
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = binding.editTitle.text.toString()
                val description = binding.editDescription.text.toString()
                replyIntent.putExtra("newTitle", title)
                replyIntent.putExtra("newDescription", description)
                replyIntent.putExtra("newId", id)
                notesViewModel.update(title, description, id)
                finish()
            }
        }
    }

    private fun notesToolbar(){
        val toolbar = binding.editNotesToolbar
        toolbar.title = "Edit your Note"
        setSupportActionBar(toolbar)
    }
}