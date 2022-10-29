package com.devzseni.crud.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.devzseni.crud.R
import com.devzseni.crud.adapter.NotesAdapter
import com.devzseni.crud.databinding.FragmentHomeBinding
import com.devzseni.crud.model.NoteViewModel
import com.devzseni.crud.model.Notes


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewModel: NoteViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        //Layout Manager
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        layoutManager.reverseLayout = false
        binding.allNotesRv.layoutManager = layoutManager
        binding.allNotesRv.hasFixedSize()


        // Get All Notes
        viewModel.getAllNotes.observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.allNotesRv.adapter = adapter
        }


        //Filter All notes
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotesRv.adapter = adapter
            }
        }

        // Get All Notes for Filtering
        binding.filterIcon.setOnClickListener {
            viewModel.getAllNotes.observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.allNotesRv.adapter = adapter
            }
        }

            //Filter Medium Notes
            binding.filterMedium.setOnClickListener {
                viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                    oldMyNotes = notesList as ArrayList<Notes>
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.allNotesRv.adapter = adapter
                }
            }
            //Filter High Notes
            binding.filterLow.setOnClickListener {
                viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                    oldMyNotes = notesList as ArrayList<Notes>
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.allNotesRv.adapter = adapter
                }
            }
            binding.fabAddNote.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_createNoteFragment)
            }
            return binding.root
        }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.search_menu, menu)

            val item = menu.findItem(R.id.app_bar_search)
            val searchView = item.actionView as android.widget.SearchView
            searchView.queryHint = "Enter Notes Here"
            searchView.setOnQueryTextListener(object :
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    notesFiltering(p0)
                    return true
                }
            })
            super.onCreateOptionsMenu(menu, inflater)
        }

        private fun notesFiltering(p0: String?) {

            val newFilteredList = arrayListOf<Notes>()

            for (i in oldMyNotes) {
                if (i.title.contains(p0!!) || i.subtitle.contains(p0)) {
                    newFilteredList.add(i)
                }
            }
            adapter.filtering(newFilteredList)
        }
    }



