package com.zannardyapps.roommvvm.ui.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zannardyapps.roommvvm.R
import com.zannardyapps.roommvvm.databinding.ActivityNewNoteBinding

class NewNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.btnAddNote.setOnClickListener {

            val replyIntent = Intent()

            if (binding.editTitle.text.isEmpty()){
                binding.editTitle.error = "required title"
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {

                val title = binding.editTitle.text.toString()
                val description = binding.editDescription.text.toString()

                replyIntent.putExtra("title", title)
                replyIntent.putExtra("description", description)

                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }

        }

    }
}