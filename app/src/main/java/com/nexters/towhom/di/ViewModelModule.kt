package com.nexters.towhom.di

import com.nexters.towhom.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Kiyeon_Kim (giyeon15@gmail.com)
 * @see [중요] View Class에서 ViewModel 호출할때 여기에 viewModel 호출 및
 * 해당 View Class에서는 getViewModel()
 * */
val viewModelModule = module {
    viewModel { LoginViewModel() }
    // viewModel { MainViewModel() }
}