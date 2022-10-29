package com.devzseni.crud.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devzseni.crud.R
import com.devzseni.crud.databinding.FragmentEditNoteBinding
import com.devzseni.crud.model.NoteViewModel
import com.devzseni.crud.model.Notes
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class EditNoteFragment : Fragment() {

    val oldNotes by navArgs<EditNoteFragmentArgs>()
    lateinit var binding: FragmentEditNoteBinding
    var priority: String = "1"
    val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding = FragmentEditNoteBinding.inflate(
            layoutInflater, container, false)

        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubtitle.setText(oldNotes.data.subtitle)
        binding.editNote.setText(oldNotes.data.notes)

        when (oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_done)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_done)
                binding.pGreen.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_done)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
        }

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

        binding.fabEditSaveNote.setOnClickListener {
            updateNote(it)
        }
        return binding.root


    }



    private fun updateNote(it: View?) {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubtitle.text.toString()
        val notes = binding.editNote.text.toString()
        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.getDefault())
        val currentDate:String = sdf.format(Date())

        val data = Notes(
            oldNotes.data.id,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = currentDate,
            priority
        )

        viewModel.updateNotes(data)
        Toast.makeText(requireContext(), "Notes Updated Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){

            val bottomSheet =
                BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data)
                findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
            }

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}

