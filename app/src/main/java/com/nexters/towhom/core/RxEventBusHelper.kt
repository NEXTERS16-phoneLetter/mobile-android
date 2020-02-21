package com.nexters.towhom.core

import com.nexters.towhom.vo.FontVO
import io.reactivex.subjects.PublishSubject

object RxEventBusHelper {
    var count = 1
    var focus = -1

    val themeSubject: ArrayList<PublishSubject<Int>> = arrayListOf(PublishSubject.create<Int>())
    val fontSubject: ArrayList<PublishSubject<FontVO>> = arrayListOf(PublishSubject.create<FontVO>())
    val fontColorSubject: ArrayList<PublishSubject<Int>> = arrayListOf(PublishSubject.create<Int>())

    fun sendThemeEvent(themeResource: Int) {
        themeSubject[focus].onNext(themeResource)
    }

    fun sendFontEvent(vo: FontVO) {
        fontSubject[focus].onNext(vo)
    }

    fun sendFontColorEvent(fontColorResource: Int) {
        fontColorSubject[focus].onNext(fontColorResource)
    }

    fun addSubject() {
        themeSubject.add(PublishSubject.create<Int>())
        fontSubject.add(PublishSubject.create())
        fontColorSubject.add(PublishSubject.create())
    }

    fun removeSubject() {
        themeSubject.removeAt(count - 1)
        fontSubject.removeAt(count - 1)
        fontColorSubject.removeAt(count -1)
    }

}