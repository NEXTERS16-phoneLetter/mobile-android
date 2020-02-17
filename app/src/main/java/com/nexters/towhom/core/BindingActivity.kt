package com.nexters.towhom.core


import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.nexters.towhom.R

abstract class BindingActivity<T : ViewDataBinding> : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected lateinit var binding: T
        private set

    var backPressedListener: OnBackPressedListener? = null

    interface OnBackPressedListener {
        fun onBackPressed()
    }


    /** SharedPreference 변수 */
    var pref: SharedPreferences? = null
    /** 뒤로가기 눌렀을 때, 인터벌(default : 2초) */
    val FINISH_INTERVAL_TIME: Int = 2000
    /** 뒤로가기 횟수 저장 변수 */
    var BACKPRESSED_TIME: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutResId())
    }

    fun setOnKeyBackPressedListener(listener: OnBackPressedListener) {
        this.backPressedListener = listener
    }

    fun removeOnKeyBackPressedListener() {
        this.backPressedListener = null
    }

    override fun onBackPressed() {
        if (backPressedListener != null) {
            backPressedListener!!.onBackPressed()
        } else {
            super.onBackPressed()
        }

    }
    /*  override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - BACKPRESSED_TIME

        if (intervalTime in 0..FINISH_INTERVAL_TIME) {
            this.finish()
//            ActivityCompat.finishAffinity(this)
        } else {
            BACKPRESSED_TIME = tempTime
            Toast.makeText(this, resources.getString(R.string.sys_backpress_msg), Toast.LENGTH_SHORT).show()
        }
    }*/
}