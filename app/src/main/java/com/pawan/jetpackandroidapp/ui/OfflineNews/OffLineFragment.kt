package com.pawan.jetpackandroidapp.ui.OfflineNews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawan.jetpackandroidapp.R
import com.pawan.jetpackandroidapp.databinding.FragmentOfflineBinding
import com.pawan.jetpackandroidapp.ui.adapters.NewsAdapter
import com.pawan.jetpackandroidapp.utils.DataHandler
import com.pawan.jetpackandroidapp.utils.LogData
import com.pawan.jetpackandroidapp.ui.onlinenews.OfflineViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OffLineFragment : Fragment(R.layout.fragment_offline) {

    lateinit var binding: FragmentOfflineBinding

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private val offlineViewModel: OfflineViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOfflineBinding.bind(view)
        init()

        offlineViewModel.article.observe(viewLifecycleOwner, Observer { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    LogData("onViewCreated: SUCCESS  ${dataHandler.data?.get(0)?.title} ")
                    newsAdapter.differ.submitList(dataHandler.data)
                }
                is DataHandler.ERROR -> {
                    LogData("onViewCreated: ERROR ${dataHandler.message}")
                }
                is DataHandler.LOADING -> {
                    LogData("onViewCreated: LOADING")
                }
            }
        })
    }

    private fun init() {
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

}