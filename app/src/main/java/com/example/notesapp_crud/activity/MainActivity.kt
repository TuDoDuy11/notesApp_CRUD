package com.example.notesapp_crud.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp_crud.adapter.NotesAdapter
import com.example.notesapp_crud.databinding.ActivityMainBinding
import com.example.notesapp_crud.helper.NotesDatabaseHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = NotesDatabaseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(),this)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        binding.txtDangXuat.setOnClickListener {
            val intent =Intent(this,LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Log out successful",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}