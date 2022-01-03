package com.parthdesai.myapplication.presentation.business_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.parthdesai.myapplication.data.models.BusinessDto
import com.parthdesai.myapplication.repository.BusinessListRepository
import java.util.HashMap

class BusinessesListViewModel : ViewModel() {

    private lateinit var map: HashMap<String, MutableList<BusinessDto>>
    private lateinit var category: List<String>
    private var businessListRepository = BusinessListRepository.getInstance()

    init {
        fetchData(
            "",
            "Toronto"
        )
    }

    fun fetchData(
        term: String,
        location: String
    ) {
        businessListRepository.fetchData(term, location);
    }

    fun getNewsRepository(): LiveData<List<BusinessDto>> {
        return businessListRepository.businesses
    }

    fun convertListToHashMap(businesses: List<BusinessDto>) {
        map = HashMap<String, MutableList<BusinessDto>>()
        businesses.map { businessesDto ->
            businessesDto.categories.map {
                map.getOrPut(it.title) {
                    mutableListOf()
                }.add(businessesDto)
            }
        }
    }

    fun getConvertHashMap(): HashMap<String, MutableList<BusinessDto>> {
        return map
    }

    fun setCategoryList(businesses: List<BusinessDto>) {
        category = businesses
            .flatMap { it ->
                it.categories.map {
                    it.title
                }
            }
            .distinct()
    }

    fun getCategoryList(): List<String> {
        return category
    }
}