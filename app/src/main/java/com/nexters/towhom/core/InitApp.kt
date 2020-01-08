package com.nexters.towhom.core

import android.app.Application
import android.util.Log
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
    }
}