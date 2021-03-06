package com.purplepotato.kajianku.home.allkajian

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.purplepotato.kajianku.core.Resource
import com.purplepotato.kajianku.core.data.KajianRepository
import com.purplepotato.kajianku.core.domain.Kajian
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect

class AllKajianViewModel(private val repository: KajianRepository) : ViewModel() {

    val listAllKajian = liveData<Resource<List<Kajian>>>(IO) {
        emit(Resource.Loading())

        try {
            repository.queryAllKajian().collect {
                emit(it)
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString(), emptyList()))
        }
    }
}