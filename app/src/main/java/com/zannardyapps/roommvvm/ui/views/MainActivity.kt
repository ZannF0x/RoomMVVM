package com.zannardyapps.roommvvm.ui.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zannardyapps.roommvvm.application.NotesApplication
import com.zannardyapps.roommvvm.database.model.Notes
import com.zannardyapps.roommvvm.databinding.ActivityMainBinding
import com.zannardyapps.roommvvm.ui.adapter.NotesAdapter
import com.zannardyapps.roommvvm.ui.viewmodel.NotesViewModel
import com.zannardyapps.roommvvm.ui.viewmodel.NotesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter

    private val notesViewModel: NotesViewModel by viewModels {
        NotesViewModelFactory((application as NotesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        val resultLauncherNewNoteActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.let {

                        val title = it.getStringExtra("title") ?: ""
                        val description = it.getStringExtra("description") ?: ""

                        val notes: Notes = Notes(title, description)
                        notesViewModel.insert(notes)

                    }
                }
            }

        binding.fabAddNote.setOnClickListener {
            resultLauncherNewNoteActivity.launch(Intent(this, NewNoteActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()

        notesViewModel.allNotesLiveData.observe(this, { listNotes ->
            if (listNotes.isEmpty()) {
                binding.frameLayoutId.visibility = View.VISIBLE
            } else {
                binding.frameLayoutId.visibility = View.GONE
            }

            listNotes?.let { notes ->
                notesAdapter.submitList(notes)
            }
        })

    }


    override fun onResume() {
        super.onResume()

        notesViewModel.allNotesLiveData.observe(this, { listNotes ->

            if (listNotes.isEmpty()) {
                binding.frameLayoutId.visibility = View.VISIBLE
            } else {
                binding.frameLayoutId.visibility = View.GONE
            }

            listNotes?.let { notes ->
                notesAdapter.submitList(notes)
            }

        })

        notesAdapter.listenerActionRemove = { noteSelected ->
            notesViewModel.delete(noteSelected)
        }

        notesAdapter.listenerActionEdit = { noteSelected ->
            val intent = Intent(this, EditNotesActivity::class.java)
            intent.putExtra("id", noteSelected.notesId)
            intent.putExtra("title", noteSelected.notesTitle)
            intent.putExtra("description", noteSelected.notesDescription)
            startActivity(intent)
        }

    }

    private fun initRecyclerView() {
        recyclerView = binding.recyclerview
        notesAdapter = NotesAdapter()
        recyclerView.adapter = notesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}