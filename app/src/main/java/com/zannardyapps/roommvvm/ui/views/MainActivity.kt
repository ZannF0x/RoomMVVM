package com.zannardyapps.roommvvm.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zannardyapps.roommvvm.application.NotesApplication
import com.zannardyapps.roommvvm.databinding.ActivityMainBinding
import com.zannardyapps.roommvvm.ui.adapter.NotesAdapter
import com.zannardyapps.roommvvm.ui.viewmodel.NotesViewModel
import com.zannardyapps.roommvvm.ui.viewmodel.NotesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter

    private val notesViewModel: NotesViewModel by viewModels{
        NotesViewModelFactory((application as NotesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

    }

    override fun onStart() {
        super.onStart()

        

        binding.fabAddNote.setOnClickListener {
            initNewNoteActivity()
        }
    }



    private fun initRecyclerView(){
        recyclerView = binding.recyclerview
        notesAdapter = NotesAdapter()
        recyclerView.adapter = notesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initNewNoteActivity(){
        val intent = Intent(this, NewNoteActivity::class.java)
        startActivity(intent)
    }
}