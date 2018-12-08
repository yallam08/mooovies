package com.sabekwla7ek.mooovies.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.sabekwla7ek.mooovies.vvm.FragmentNavigator

/**
 * Created by Yahia Allam on 08/12/2018
 */
open class BaseFragment : Fragment() {

    lateinit var fragmentNavigator: FragmentNavigator

    override fun onAttach(context: Context?) {
        try {
            fragmentNavigator = activity as FragmentNavigator
        } catch (e: ClassCastException) {
            throw ClassCastException("${activity.toString()} must implement ${FragmentNavigator::class.simpleName}")
        }

        return super.onAttach(context)
    }
}