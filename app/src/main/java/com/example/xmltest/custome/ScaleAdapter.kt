package com.example.xmltest.custome


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xmltest.R
import com.example.xmltest.Scale


class ScaleAdapter {


    class TaskAdapter(private val tasks: List<Scale>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

        class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val taskTitle: TextView = view.findViewById(R.id.scale)
            val taskDescription: TextView = view.findViewById(R.id.taskDescription)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
            return TaskViewHolder(view)
        }

        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            val task = tasks[position]
            holder.taskTitle.text = task.title
            holder.taskDescription.text = task.description

            val spacingInPixels = holder.itemView.resources.getDimensionPixelSize(R.dimen.spacing_between_items)
            val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(spacingInPixels, spacingInPixels, spacingInPixels, spacingInPixels)
            holder.itemView.layoutParams = layoutParams
        }

        override fun getItemCount() = tasks.size
    }
}