package com.nexters.towhom.ui.write

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.viewpager2.widget.ViewPager2
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentWriteBinding
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WriteFragment : BindingFragment<FragmentWriteBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_write

    private val viewPager by lazy { binding.contentVp }
    private val indicator by lazy { binding.contentIndicator }
    /**sticker_default**/
    /**sticker_add**/
    private val mstickerLinear by lazy { binding.stickerLinear }
    private val add_sticker_btn by lazy { binding.stickerBtn }

    /**sticker_drag**/
    private val mainLayout by lazy { binding.mainConstraint }

    private val letterBtn by lazy { binding.letterBtn }
    private val textBtn by lazy { binding.textBtn }
    private val stickerBtn by lazy { binding.stickerBtn }
    private val addBtn by lazy { binding.addBtn }
    private val deleteBtn by lazy { binding.deleteBtn }


    private val bottomBarButtonList: List<AppCompatImageButton> by lazy {
        listOf(
            letterBtn,
            textBtn,
            stickerBtn,
            addBtn,
            deleteBtn
        )
    }


    /** Test Button */
//    private val testBt by lazy { binding.testBtn }

    val defaultList by lazy { resources.obtainTypedArray(R.array.default_bottom_btn_array) }
    val enableList by lazy { resources.obtainTypedArray(R.array.enable_bottom_btn_array) }

    var testList = mutableListOf<String>("list1")

    private var ACTIVATE_PAGE_NUM = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = getViewModel()

        viewPager.adapter = ContentAdapter(testList)
        indicator.createDotPanel(
            testList.size,
            R.drawable.indicator_dot_off,
            R.drawable.indicator_dot_on,
            0
        )




    }

    override fun bindingView() {
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindingEventListener() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                ACTIVATE_PAGE_NUM = position
                indicator.selectDot(position)
            }
        })

        /*  testBt.setOnClickListener {
              testList.add("list5")

              (viewPager.adapter as ContentAdapter).apply {
                  setUpdateItems(testList)
                  notifyDataSetChanged()
              }

              indicator.createDotPanel(testList.size, R.drawable.indicator_dot_off, R.drawable.indicator_dot_on, ACTIVATE_PAGE_NUM)
          }*/

        add_sticker_btn.setOnClickListener {
            val stickerImage =
                LayoutInflater.from(context).inflate(R.layout.sticker, mstickerLinear, false)


            mstickerLinear.addView(stickerImage)
        }
        mstickerLinear.setOnTouchListener((View.OnTouchListener { view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                view.y = motionEvent.rawY - view.height / 2
                view.x = motionEvent.rawX - view.width / 2
            }
            true

        }))



        letterBtn.setOnClickListener {
            selectedButtonImageChange(it)
        }
        textBtn.setOnClickListener {

            //            letterBtn.setImageResource(R.drawable.text_enable)
            selectedButtonImageChange(it)
        }
        stickerBtn.setOnClickListener {

            selectedButtonImageChange(it)
        }

        /** Touch Event
         * 빨간불 깜빡임 기능 하기 위해
         *
         * */
        addBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> selectedButtonImageChange(v)
                MotionEvent.ACTION_UP -> {
                    // 5장 이하일때만 추가가
                    if (testList.size < 5) {
                        (v as AppCompatImageButton).setImageResource(R.drawable.letter_add_default)
                        testList.add("tempList") //TODO : 여기 리스트 내용 변경해야함
                        updateLetterPaperStatus()
                    } else {
                        Toast.makeText(context, "편지지 추가는 5장이 최대에요 :=)", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            true
        }

        deleteBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> selectedButtonImageChange(v)
                MotionEvent.ACTION_UP -> {
                    if (testList.size > 1) {
                        (v as AppCompatImageButton).setImageResource(R.drawable.delete_default)
                        testList.removeAt(testList.size - 1)
                        updateLetterPaperStatus()
                    } else {
                        Toast.makeText(context, "편지지가 1장밖에 안남았어요 :=)", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            true
        }

    }


    override fun bindingObserver() {
    }


    private fun selectedButtonImageChange(clickView: View) {
        val clickBtn = clickView as AppCompatImageButton

        for ((i, btn) in bottomBarButtonList.withIndex()) {
            if (btn == clickBtn) {
                btn.setImageResource(enableList.getResourceId(i, -1))
            } else {
                btn.setImageResource(defaultList.getResourceId(i, -1))
            }
        }
    }

    private fun updateLetterPaperStatus() {
        (viewPager.adapter as ContentAdapter).apply {
            setUpdateItems(testList)
            notifyDataSetChanged()
        }

        indicator.createDotPanel(
            testList.size,
            R.drawable.indicator_dot_off,
            R.drawable.indicator_dot_on,
            ACTIVATE_PAGE_NUM
        )
    }

    // 키보드 올라왔는지 확인해주는 기능 ver java
/*
    contentView.getViewTreeObserver().addOnGlobalLayoutListener(new     ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {

            Rect r = new Rect();
            contentView.getWindowVisibleDisplayFrame(r);
            int screenHeight = contentView.getRootView().getHeight();

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            int keypadHeight = screenHeight - r.bottom;

            Log.d(TAG, "keypadHeight = " + keypadHeight);

            if (keypadHeight &gt; screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
            // keyboard is opened
        }
            else {
            // keyboard is closed
        }
        }
    });

*/


}