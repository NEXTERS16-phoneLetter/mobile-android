package com.nexters.towhom.di

import com.nexters.towhom.ui.home.HomeViewModel
import com.nexters.towhom.ui.signin.SigninViewModel
import com.nexters.towhom.ui.login.LoginHomeViewModel
import com.nexters.towhom.ui.preview.PreviewViewModel
import com.nexters.towhom.ui.signup.PhoneCertificationViewModel
import com.nexters.towhom.ui.signup.SignupViewModel
import com.nexters.towhom.ui.write.WriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Kiyeon_Kim (giyeon15@gmail.com)
 * @see [중요] View Class에서 ViewModel 호출할때 여기에 viewModel 호출 및
 * 해당 View Class에서는 getViewModel()
 * */
val viewModelModule = module {
    viewModel { SigninViewModel() }
    viewModel { LoginHomeViewModel() }
    viewModel { HomeViewModel() }
    viewModel { WriteViewModel() }
    viewModel { SignupViewModel() }
    viewModel { PhoneCertificationViewModel() }
    viewModel { PreviewViewModel() }
    // viewModel { MainViewModel() }
}