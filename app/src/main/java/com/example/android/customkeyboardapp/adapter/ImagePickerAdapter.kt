package com.example.android.customkeyboardapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.customkeyboardapp.picker.PhotoPickerFragment
import com.example.android.customkeyboardapp.R
import com.example.android.customkeyboardapp.loaders.ImageLoader
import kotlinx.android.synthetic.main.view_pickable_image.view.*

class ImagePickerAdapter(
    private val onImageClick: (SelectableImage) -> Unit,
    private val multiple: Boolean,
    private val imageLoader: ImageLoader,
    private val fragment: PhotoPickerFragment
) : ListAdapter<SelectableImage, ImagePickerAdapter.ImagePickerViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) =
        ImagePickerViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_pickable_image, parent, false)
                .apply { checkbox.visibility = if (multiple) View.VISIBLE else View.GONE }
        )

    override fun onBindViewHolder(
        holder: ImagePickerViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        payloads.firstOrNull()?.let {
            holder.view.checkbox.isChecked = getItem(position).selected
        } ?: super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: ImagePickerViewHolder, position: Int) {
        val item = getItem(position)
        holder.view.apply {
            imageLoader.loadImage(context, photo_item, item.uri)
            setOnClickListener { onImageClick(getItem(position)) }
            checkbox.isChecked = item.selected
        }
        if (item.id == 0) {
            // View Gallery
            holder.view.apply {
                checkbox.visibility = View.GONE
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                        addCategory(Intent.CATEGORY_OPENABLE)
                        type = "image/*"
                        addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                        putExtra(Intent.EXTRA_ALLOW_MULTIPLE, multiple)
                    }
                    fragment.startActivityForResult(
                        Intent.createChooser(intent, context.getString(R.string.picker_select_photo)),
                        2
                    )
                }
            }
        }
    }

    class ImagePickerViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    companion object {
        private const val SELECTED_PAYLOAD = "selected_payload"

        private val DiffCallback = object : DiffUtil.ItemCallback<SelectableImage>() {
            override fun areItemsTheSame(
                oldItem: SelectableImage,
                newItem: SelectableImage
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: SelectableImage,
                newItem: SelectableImage
            ): Boolean = oldItem == newItem

            override fun getChangePayload(
                oldItem: SelectableImage,
                newItem: SelectableImage
            ): Any? = when {
                oldItem.selected != newItem.selected -> SELECTED_PAYLOAD
                else -> null
            }
        }
    }

}