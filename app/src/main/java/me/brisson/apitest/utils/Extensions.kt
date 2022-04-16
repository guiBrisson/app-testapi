package me.brisson.apitest.utils

import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup

fun View.show() {
    (parent as? ViewGroup)?.let { TransitionManager.beginDelayedTransition(it) }
    visibility = View.VISIBLE
}

fun View.hide() {
    (parent as? ViewGroup)?.let { TransitionManager.beginDelayedTransition(it) }
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}