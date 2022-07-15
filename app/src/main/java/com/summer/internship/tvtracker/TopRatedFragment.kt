package com.summer.internship.tvtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summer.internship.tvtracker.databinding.FragmentTopRatedBinding

class TopRatedFragment : Fragment() {
    private lateinit var binding: FragmentTopRatedBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }
}