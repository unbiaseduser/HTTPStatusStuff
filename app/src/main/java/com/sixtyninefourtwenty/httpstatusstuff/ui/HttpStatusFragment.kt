package com.sixtyninefourtwenty.httpstatusstuff.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.net.toUri
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sixtyninefourtwenty.httpstatusstuff.R
import com.sixtyninefourtwenty.httpstatusstuff.data.HttpStatusCodeInfo
import com.sixtyninefourtwenty.httpstatusstuff.databinding.ListItemStatusCodeBinding

abstract class HttpStatusFragment : RecyclerViewFragment() {

    open fun onItemClick(info: HttpStatusCodeInfo) {
        requireContext().startActivity(Intent(Intent.ACTION_VIEW).setData(info.url().toUri()))
    }

    val infoAdapter: ListAdapter<HttpStatusCodeInfo, *> = HttpStatusAdapter(::onItemClick)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = infoAdapter
        }
    }

    private class HttpStatusAdapter(
        private val onClick: (HttpStatusCodeInfo) -> Unit
    ) : ListAdapter<HttpStatusCodeInfo, HttpStatusAdapter.ViewHolder>(DIFFER) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(ListItemStatusCodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)) {
                onClick(getItem(it))
            }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        class ViewHolder(
            private val binding: ListItemStatusCodeBinding,
            onClick: (Int) -> Unit
        ) : RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener { onClick(adapterPosition) }
            }

            fun bind(info: HttpStatusCodeInfo) {
                binding.img.load(info.imageUrl()) {
                    error(R.drawable.error)
                    placeholder(R.drawable.hourglass_bottom)
                    crossfade(true)
                    listener(
                        onStart = { _ ->
                            binding.img.updateLayoutParams<ViewGroup.LayoutParams> {
                                height = binding.img.width
                            }
                        },
                        onSuccess = { _, _ ->
                            binding.img.updateLayoutParams<ViewGroup.LayoutParams> {
                                height = ViewGroup.LayoutParams.WRAP_CONTENT
                            }
                        }
                    )
                }
                binding.code.text = info.code.toString()
                binding.desc.text = info.label(binding.root.context)
            }
        }

        companion object {
            private val DIFFER = object : DiffUtil.ItemCallback<HttpStatusCodeInfo>() {
                override fun areItemsTheSame(
                    oldItem: HttpStatusCodeInfo,
                    newItem: HttpStatusCodeInfo
                ): Boolean = oldItem.code == newItem.code

                override fun areContentsTheSame(
                    oldItem: HttpStatusCodeInfo,
                    newItem: HttpStatusCodeInfo
                ): Boolean = oldItem.code == newItem.code

            }
        }

    }

}