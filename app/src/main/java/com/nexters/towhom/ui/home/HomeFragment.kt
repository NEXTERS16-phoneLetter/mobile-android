package com.nexters.towhom.ui.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.findNavController
import com.nexters.towhom.MainActivity
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingActivity
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel


class HomeFragment : BindingFragment<FragmentHomeBinding>(), BindingActivity.OnBackPressedListener {
    override fun getLayoutResId() = R.layout.fragment_home

    private var BACKPRESSED_TIME: Long = 0
    private val FINISH_INTERVAL_TIME: Int = 2000


    private lateinit var letterImage: AppCompatImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = getViewModel()
        letterImage = binding.letterStartImage


        (activity as MainActivity).setOnKeyBackPressedListener(this) //backPressed 처리
        //binding.userName.text = "From. " + CommonData.USER_NAME
        //GlideBindingAdapter.setProfileImage(binding.userImage, CommonData.USER_IMAGE_URL)


    }

    override fun bindingView() {
        // writeBtn = binding.writeContainer
    }


    /**
     * @author hyness
     * @issue 1. 편지가 제자리로 안돌아간다.
     * 2.
     * */
    override fun bindingEventListener() {

        var defaultPosition = 0F

        letterImage.setOnTouchListener { v, event ->
            defaultPosition = v.width / 1.25f
            Log.d("asdf",event.rawX.toString())

            when (event.action) {

                MotionEvent.ACTION_MOVE -> {
                    v.x = event.rawX - v.width / 4
                }
                MotionEvent.ACTION_UP -> {
                    if (v.x < 600) {
                        findNavController().navigate(R.id.action_home_to_write)
                    } else {
                        v.x = defaultPosition
//                        eggAnimator1 = CommonController.getInstance().setAnimator(starEgg,0.7f,0, 250, delay);
//                        iewPropertyAnimator.animate(view).translationXBy(0).setDuration(0)
                    }
                }
            }


            true

        }


    }

    fun setAnimator(obj: AppCompatImageView, sizeXY: Float, transX: Int, transY: Int, duration: Int
    ): AnimatorSet? {
        val scaleX = ObjectAnimator.ofFloat(obj, "scaleX", sizeXY)
        val scaleY = ObjectAnimator.ofFloat(obj, "scaleY", sizeXY)
        val alpha = ObjectAnimator.ofFloat(obj, "alpha", 0.0f, 1f)
        val translationX =
            ObjectAnimator.ofFloat(obj, "translationX", transX.toFloat())
        val translationY =
            ObjectAnimator.ofFloat(obj, "translationY", transY.toFloat())
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY, alpha, translationX, translationY)
        animatorSet.duration = duration.toLong()
        return animatorSet
    }


    override fun bindingObserver() {
    }

    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - BACKPRESSED_TIME

        if (intervalTime in 0..FINISH_INTERVAL_TIME) {
            activity!!.finish()
        } else {
            BACKPRESSED_TIME = tempTime
            Toast.makeText(
                context,
                resources.getString(R.string.sys_backpress_msg),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}
