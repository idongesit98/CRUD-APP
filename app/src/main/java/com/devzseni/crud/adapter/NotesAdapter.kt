package com.devzseni.crud.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.devzseni.crud.R
import com.devzseni.crud.databinding.ItemNoteBinding
import com.devzseni.crud.model.Notes
import com.devzseni.crud.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }
    inner class NotesViewHolder(val binding: ItemNoteBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
       return NotesViewHolder(
           ItemNoteBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,false
           )
       )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.noteTitle.text = data.title
        holder.binding.noteSubtitle.text = data.subtitle
        holder.binding.noteDate.text = data.date

        when(data.priority){
            "1" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = notesList.size
}