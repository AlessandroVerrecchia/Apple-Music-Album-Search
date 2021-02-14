package com.alessandro.musicsearch.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alessandro.musicsearch.R
import com.alessandro.musicsearch.ui.AlbumsAdapter
import com.alessandro.musicsearchapi.network.model.AlbumDto
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), IAlbumClickListener {

    private val searchViewModel by viewModel<SearchViewModel>()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeSearchResult()
        observeError()
        setupSearchInputListener()
    }

    private fun observeSearchResult() {
        searchViewModel.albumsLiveData.observe(viewLifecycleOwner, Observer { albums ->
            if (albums.isEmpty()) {
                Toast.makeText(context, "This artist is too underground.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                albumsAdapter.updateList(albums)
            }
        })
    }

    private fun observeError() {
        searchViewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })
    }

    private fun search() {
        searchViewModel.searchAlbums(searchView.query.toString())
    }

    private fun clearList() {
        albumsAdapter.updateList(emptyList())
    }

    private fun setupRecyclerView() {
        searchResultRecyclerView.layoutManager = LinearLayoutManager(context)
        albumsAdapter = AlbumsAdapter(this)
        searchResultRecyclerView.adapter = albumsAdapter
    }

    private fun setupSearchInputListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    clearList()
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                search()
                return false
            }

        })
    }

    override fun onClick(albumDto: AlbumDto) {
        createAlbumDialog(albumDto)
    }

    private fun createAlbumDialog(albumDto: AlbumDto) {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(albumDto.name)
                .setMessage(
                    "primary genre name : ${albumDto.primaryGenreName} \n" +
                            "collection price : ${albumDto.collectionPrice} \n" +
                            "Currency : ${albumDto.currency} \n" +
                            "Copyright : ${albumDto.copyright}"
                )
                .setPositiveButton("ok") { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .create()
                .show()
        }
    }

}