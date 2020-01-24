package com.nexters.towhom.core

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.kakao.auth.*
import com.nexters.towhom.di.networkModule
import com.nexters.towhom.di.viewModelModule
import org.koin.core.context.startKoin

/**
 * @author Kiyeon Kim (giyeon15@gmail.com)
 * @version 1.0, 2020. 01. 08
 * @see Manifast -> <application
 *                      android:name="package.this.className" /> ex)  android:name="com.kolon.psam.InitApp"
 *                      추가 필수 (Koin 의존성 주입)
 */
class InitApp : Application() {
    class KakaoSDKAdapter(private val context: Context) : KakaoAdapter() {
        override fun getApplicationConfig(): IApplicationConfig {
            return IApplicationConfig {
                return@IApplicationConfig context
            }
        }

        override fun getSessionConfig(): ISessionConfig = object : ISessionConfig {
            override fun isSaveFormData(): Boolean = false

            override fun getAuthTypes(): Array<AuthType> = arrayOf(AuthType.KAKAO_TALK_ONLY)

            override fun isSecureMode(): Boolean = false

            override fun getApprovalType(): ApprovalType? = ApprovalType.INDIVIDUAL

            override fun isUsingWebviewTimer(): Boolean = false

        }

    }

    override fun onCreate() {
        super.onCreate()

        /***/
        startKoin {
            modules(
                listOf(
                    networkModule,
                    viewModelModule
                )
            )
            Log.e("Koin", "Koin start!!")
        }

        KakaoSDK.init(KakaoSDKAdapter(this))
    }
}