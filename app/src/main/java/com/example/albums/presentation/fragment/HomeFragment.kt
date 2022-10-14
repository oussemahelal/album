package com.example.albums.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albums.BaseApplication
import com.example.albums.R
import com.example.albums.data.room.db.AppDatabase
import com.example.albums.data.room.model.DataModel
import com.example.albums.di.main.extension.injectViewModel
import com.example.albums.presentation.adapter.AlbumAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.exitProcess

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var exitButton: AppCompatButton

    private lateinit var viewModel: HomeViewModel

    private lateinit var albumAdapter: AlbumAdapter

    @Inject
    lateinit var appDatabase: AppDatabase

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

        viewModel.getFromDatabase()
        viewModel.getAlbums()

        exitButton.setOnClickListener {
            exitProcess(0)
        }

        handleAlbumResult()

        return root
    }

    /**
     * Handle data observer && update Ui
     */
    private fun handleAlbumResult() {
        viewModel.albumsResult.observe(viewLifecycleOwner) {

            if (it.albums.isNotEmpty()) {
                updateUi(it.albums.map { m -> m.title ?: "" })

                CoroutineScope(Dispatchers.IO).launch {
                    val data: ArrayList<DataModel> = arrayListOf()
                    it.albums.forEach { m -> data.add(DataModel(m)) }
                    appDatabase.appDao().createAll(data)
                }
            }
        }
    }

    /**
     * Update ui && display data
     */
    private fun updateUi(list: List<String>) {
        initRecycler(list)
        progressBar.visibility = View.GONE
    }

    /**
     * Initialize adapter
     */
    private fun initRecycler(data: List<String>) {
        albumAdapter = AlbumAdapter(requireContext(), data)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = albumAdapter

    }

    /**
     * Initialize views
     */
    private fun initViews(root: View) {
        recyclerView = root.findViewById(R.id.home_fragment_recycler_view)
        exitButton = root.findViewById(R.id.home_fragment_exit_btn)
        progressBar = root.findViewById(R.id.home_fragment_progress_bar)
        progressBar.visibility = View.VISIBLE
    }

}