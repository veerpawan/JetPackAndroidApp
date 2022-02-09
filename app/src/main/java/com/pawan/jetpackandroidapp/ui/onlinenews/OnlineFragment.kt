package com.pawan.jetpackandroidapp.ui.onlinenews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawan.jetpackandroidapp.R
import com.pawan.jetpackandroidapp.databinding.FragmentOnlineBinding
import com.pawan.jetpackandroidapp.ui.adapters.NewsAdapter
import com.pawan.jetpackandroidapp.utils.DataHandler
import com.pawan.jetpackandroidapp.utils.LogData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnlineFragment :  Fragment(R.layout.fragment_online) {

    private lateinit var binding: FragmentOnlineBinding

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private val onlineViewModel: OnlineViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnlineBinding.bind(view)
        init()

        onlineViewModel.topHeadlines.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.differ.submitList(dataHandler.data?.articles)
                }
                is DataHandler.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    LogData("onViewCreated: ERROR " + dataHandler.message)
                }
                is DataHandler.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    LogData("onViewCreated: LOADING..")

                }
            }

        }
        onlineViewModel.getTopHeadlines()

    }

    private fun init() {
        newsAdapter.onArticleClicked {
            val bundle = Bundle().apply {
                putParcelable("article_data", it)
            }
            findNavController().navigate(
                R.id.action_onlineFragment_to_articleDetailsFragment,
                bundle
            )
        }

        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }
}