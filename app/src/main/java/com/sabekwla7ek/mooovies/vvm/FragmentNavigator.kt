package com.sabekwla7ek.mooovies.vvm

import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by Yahia Allam on 30/05/2018
 */
interface FragmentNavigator {

    fun navigateToFragment(fragment: Fragment, sharedElement: View)

    fun navigateToFragmentAndSaveState(currentFragment: Fragment, newFragment: Fragment, sharedElement: View)

}