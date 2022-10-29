package com.devzseni.crud.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.devzseni.crud.R
import com.devzseni.crud.databinding.FragmentCreateNoteBinding
import com.devzseni.crud.model.NoteViewModel
import com.devzseni.crud.model.Notes
import java.text.SimpleDateFormat
import java.util.*


class CreateNoteFragment : Fragment() {
    lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)


        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_done)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_done)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_done)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.fabSaveNote.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.createNoteTitle.text.toString()
        val subtitle = binding.createSubtitle.text.toString()
        val notes = binding.NoteDescription.text.toString()
        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm",Locale.getDefault())
        val currentDate:String = sdf.format(Date())

        val data = Notes(
            null,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = currentDate,
            priority
        )
        Log.e("CN", "updateNotes: Title: $title Subtitle: $subtitle notes: $notes")

        viewModel.addNotes(data)
        Toast.makeText(requireContext(), "Notes Created Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)

    }

}