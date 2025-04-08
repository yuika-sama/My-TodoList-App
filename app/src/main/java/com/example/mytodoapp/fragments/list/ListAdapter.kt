package com.example.mytodoapp.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodoapp.R
import com.example.mytodoapp.data.model.Priority
import com.example.mytodoapp.data.model.ToDoData


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTxt: TextView = itemView.findViewById(R.id.title_txt)
        val descriptionTxt: TextView = itemView.findViewById(R.id.description_txt)
        val rowBackground: ConstraintLayout = itemView.findViewById(R.id.row_background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.titleTxt.text = currentItem.title
        holder.descriptionTxt.text = currentItem.description
        holder.rowBackground.setOnClickListener{
            val action = ListFragmentDirections.actionFragmentListToFragmentUpdate(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }
        val priorityIndicator = holder.itemView.findViewById<CardView>(R.id.priority_indicator)

        when (dataList[position].priority) {
            Priority.HIGH -> priorityIndicator.setCardBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.red)
            )
            Priority.MEDIUM -> priorityIndicator.setCardBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.yellow)
            )
            Priority.LOW -> priorityIndicator.setCardBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.green)
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(toDoData: List<ToDoData>){
        this.dataList = toDoData
        notifyDataSetChanged()
    }

}