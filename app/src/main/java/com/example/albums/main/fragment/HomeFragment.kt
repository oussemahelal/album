package com.example.albums.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.BaseApplication
import com.airbnb.lottie.LottieAnimationView
import com.example.albums.R
import com.example.albums.di.extension.injectViewModel
import com.example.albums.main.adapter.AlbumAdapter
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var screenTitle: TextView
    private lateinit var animation: LottieAnimationView

    private lateinit var viewModel: HomeViewModel
    private lateinit var albumAdapter: AlbumAdapter

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as BaseApplication).mainComponent().inject(this)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = injectViewModel(providerFactory)
        initViews(root)

        viewModel.getAlbums()

        viewModel.albumsResult.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                initRecycler(it.map { m -> m.title ?: "" })
                animation.cancelAnimation()
                animation.visibility = View.GONE
            }
        }
        return root
    }

    private fun initRecycler(data: List<String>) {
        albumAdapter = AlbumAdapter(requireContext(), data)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = albumAdapter

    }

    private fun initViews(root: View) {
        screenTitle = root.findViewById(R.id.home_fragment_title_screen)
        recyclerView = root.findViewById(R.id.home_fragment_recycler_view)
        animation = root.findViewById(R.id.home_fragment_animation)
        animation.visibility = View.VISIBLE
        animation.playAnimation()
    }

}