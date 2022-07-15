package com.summer.internship.tvtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summer.internship.tvtracker.databinding.FragmentTopRatedBinding

private lateinit var binding: FragmentTopRatedBinding
class TopRatedFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }
}