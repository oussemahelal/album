package com.example.albums.domain.usecase.getalbums


import com.example.albums.common.Resource
import com.example.albums.data.api.model.AlbumResponseModel
import com.example.albums.data.api.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAlbumsUseCase
@Inject
constructor(
    private val apiRepository: ApiRepository
) {
    operator fun invoke(): Flow<Resource<List<AlbumResponseModel>>> = flow {
        try {
            emit(Resource.Loading())
            val listAlbums = apiRepository.getListAlbums()
            emit(Resource.Success(listAlbums))

        } catch (e: HttpException) {
            emit(Resource.GenericError(e.localizedMessage ?: " An error occurred"))
        } catch (e: IOException) {
            emit(Resource.GenericError("Check your internet connection"))
        }
    }
}