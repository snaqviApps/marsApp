/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.MarsProperty

// TODO: add onClickListener parameter to adapter declaration
class PhotoGridAdapter(val onClickListener: OnClickListener) :
        ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder> (DiffCallback){

    /** binds MarsProperty to Layout */
    class MarsPropertyViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty) {
            binding.propertyV = marsProperty
            binding.executePendingBindings()                                  // execute immediately, saves recyclerView extra calculation
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem     // true iff 'object-references' are the same
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MarsPropertyViewHolder {
       return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)

        // TODO: call onClick function from OnClickListener in a lambda from setOnclickListener
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }

    // TODO: create an internal OnClickListener class with a lambda in its constructor that initializes a matching onClick function
    class OnClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit) {
//        fun onClick(marsProperty: MarsProperty) = clickListener(marsProperty)
        fun onClick(marsProperty: MarsProperty) {                                   /** equivalent to above, and works :) */
                return clickListener(marsProperty)
       }

    }

}