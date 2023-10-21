package com.sixtyninefourtwenty.httpstatusstuff.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

open class RecyclerViewFragment : Fragment() {

    private var _recyclerView: RecyclerView? = null
    val recyclerView get() = _recyclerView!!

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _recyclerView = RecyclerView(requireContext())
        return recyclerView
    }

    final override fun onDestroyView() {
        super.onDestroyView()
        _recyclerView = null
    }

}