package com.parthdesai.myapplication.presentation.business_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.parthdesai.myapplication.BuildConfig
import com.parthdesai.myapplication.databinding.BusinessListMainBinding
import timber.log.Timber
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.parthdesai.myapplication.presentation.business_list.adapter.ParentAdapter

class BusinessListActivity : AppCompatActivity() {

    private val viewModel: BusinessesListViewModel by viewModels()

    private lateinit var binding: BusinessListMainBinding

    private lateinit var parentAdapter: ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        searchEditText()
        searchDefaultList()
    }

    private fun setup() {
        binding = BusinessListMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun searchDefaultList() {
        viewModel.getNewsRepository().observe(this, {
            it?.let {
                viewModel.convertListToHashMap(it)
                viewModel.setCategoryList(it)
//                for (business: BusinessDto in it) {
//                    //Timber.d("Business ${business.name} Category ${business.categories}")
//
//                }

                nestedRecycleView()
            } ?: run {
                Timber.e("error")
            }
        })
    }

    private fun searchEditText() {
        binding.searchTerm.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.location.text.isNullOrEmpty()) {
                    Toast.makeText(this, "please enter location", Toast.LENGTH_LONG).show()
                } else {
                    viewModel.fetchData(
                        v.text.toString(),
                        binding.location.text.toString()
                    )
                }
                return@OnEditorActionListener true
            }
            false
        })

        binding.location.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (v.text.isNullOrEmpty()) {
                    Toast.makeText(this, "please enter location", Toast.LENGTH_LONG).show()
                } else {
                    viewModel.fetchData(
                        binding.searchTerm.text.toString(),
                        v.text.toString()
                    )
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun nestedRecycleView() {
        val mapOfBusinesses = viewModel.getConvertHashMap()
        val categoryList = viewModel.getCategoryList()
        parentAdapter = ParentAdapter(
            this,
            categoryList,
            mapOfBusinesses
        )
        val layoutManager = LinearLayoutManager(this)
        binding.businessList.RVparent.layoutManager = layoutManager
        binding.businessList.RVparent.adapter = parentAdapter
    }
}