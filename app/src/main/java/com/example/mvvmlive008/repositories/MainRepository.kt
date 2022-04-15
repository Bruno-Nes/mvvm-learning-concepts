package com.example.mvvmlive008.repositories

import com.example.mvvmlive008.rest.RetrofitService
//lidar com os dados da API
class MainRepository constructor(private val retrofitService: RetrofitService){
    fun getAllLives() = retrofitService.getAllLives()
}