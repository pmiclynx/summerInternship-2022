package com.summer.internship.tvtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summer.internship.tvtracker.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }
}