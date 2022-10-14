package com.example.albums.presentation.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albums.R
import com.example.albums.data.api.model.AlbumResponseModel
import com.squareup.picasso.Picasso


class AlbumAdapter internal constructor(context: Context?, data: List<AlbumResponseModel>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var listAlbums: List<AlbumResponseModel>
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        listAlbums = data
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var albumTitle: TextView
        var imageView: ImageView

        init {
            albumTitle = itemView.findViewById(R.id.album_adapter_title_text)
            imageView = itemView.findViewById(R.id.album_adapter_image_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.album_title_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listAlbums[position]

        if (!item.thumbnailUrl.isNullOrEmpty()) {
            Picasso.get()
                .load(item.thumbnailUrl)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView)
        } else {
            Picasso.get()
                .load(R.drawable.ic_launcher_background)
                .into(holder.imageView)
        }

        holder.albumTitle.text = item.title?.replaceFirstChar { m -> m.uppercase() }
    }

    override fun getItemCount(): Int {
        return listAlbums.size
    }
}