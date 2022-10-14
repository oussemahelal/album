package com.example.albums.presentation.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albums.R


class AlbumAdapter internal constructor(context: Context?, data: List<String>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var listTitles: List<String>
    private val inflater: LayoutInflater

    // data is passed into the constructor
    init {
        inflater = LayoutInflater.from(context)
        listTitles = data
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var albumTitle: TextView
        var positionView: TextView

        init {
            albumTitle = itemView.findViewById(R.id.album_adapter_title_text)
            positionView = itemView.findViewById(R.id.album_adapter_position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.album_title_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listTitles[position]
        holder.albumTitle.text = item.replaceFirstChar { m -> m.uppercase() }
        holder.positionView.text = (position + 1).toString().plus(".")
    }

    override fun getItemCount(): Int {
        return listTitles.size
    }
}