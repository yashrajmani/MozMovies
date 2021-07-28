package com.yash.home

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide


class MovieListAdapter(private val listener:MovieItemClicked ):RecyclerView.Adapter<MovieViewHolder>()

{
    private val items:ArrayList<Movie> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//        TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        val viewHolder=MovieViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val currentItem=items[position]
        holder.titleView.text=currentItem.title
        holder.date.text=currentItem.release_date
        holder.overview.text=currentItem.overview


    }

    override fun getItemCount(): Int
    {
//        TODO("Not yet implemented")
        return items.size
    }

    fun updateMovie(updatedMovie: ArrayList<Movie>)
    {
        items.clear()
        items.addAll(updatedMovie)

        notifyDataSetChanged()

    }

}

class MovieViewHolder(itemView: View) : ViewHolder(itemView)
{
    val titleView:TextView=itemView.findViewById(R.id.title)

    val date: TextView=itemView.findViewById(R.id.release_date)

    val overview:TextView=itemView.findViewById(R.id.overview)

}

interface MovieItemClicked {
    fun onItemClicked(item:Movie)


}