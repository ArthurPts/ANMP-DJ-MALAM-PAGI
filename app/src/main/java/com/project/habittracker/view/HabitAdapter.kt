package com.project.habittracker.view

import android.R
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.habittracker.databinding.ItemHabitBinding
import com.project.habittracker.model.Habit

class HabitAdapter(
    private val list: MutableList<Habit>,
    private val onUpdate: (Habit) -> Unit,
    private val onEdit: (Habit) -> Unit
) : RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habit = list[position]
        holder.binding.habit = habit

//        holder.binding.txtName.text = habit.name
//        holder.binding.txtDesc.text = habit.description

//        holder.binding.progressBar.max = habit.target
//        holder.binding.progressBar.progress = habit.progress

        val resId = holder.itemView.context.resources.getIdentifier(
            habit.iconName,
            "drawable",
            holder.itemView.context.packageName
        )

        if (resId != 0) {
            holder.binding.imgHabit.setImageResource(resId)
        } else {
            holder.binding.imgHabit.setImageResource(R.drawable.ic_menu_info_details)
        }
        if (habit.progress >= habit.target) {
//            holder.binding.txtStatus.text = "Completed"
            holder.binding.txtStatus.setTextColor(0xFF2E7D32.toInt())
            holder.binding.btnPlus.isEnabled = false

            holder.binding.progressBar.progressTintList =
                ColorStateList.valueOf(0xFF4CAF50.toInt())

        } else {
//            holder.binding.txtStatus.text = "In Progress"
            holder.binding.txtStatus.setTextColor(0xFF757575.toInt())
//                holder.binding.btnMinus.isEnabled = false
            holder.binding.btnPlus.isEnabled = true
            holder.binding.btnMinus.isEnabled = habit.progress > 0

            holder.binding.progressBar.progressTintList =
                ColorStateList.valueOf(0xFF7C4DFF.toInt())
        }

//        holder.binding.txtProgress.text = "${habit.progress} / ${habit.target} ${habit.unit}"

        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.target) {
                habit.progress += habit.step
                if (habit.progress > habit.target) habit.progress = habit.target
                notifyItemChanged(position)
                onUpdate(habit)
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            if (habit.progress > 0) {
                habit.progress -= habit.step
                if (habit.progress < 0) habit.progress = 0
                notifyItemChanged(position)
                onUpdate(habit)
            }
//                holder.binding.btnPlus.isEnabled = true

        }

        holder.binding.txtName.setOnClickListener {
            onEdit(habit)
        }
    }

    override fun getItemCount(): Int = list.size
}