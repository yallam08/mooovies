package com.sabekwla7ek.mooovies.vvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.vvm.movieslist.MoviesListFragment

class MainActivity : AppCompatActivity(), FragmentNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(MoviesListFragment(), addToBackStack = false)
    }

    override fun navigateToFragment(fragment: Fragment, sharedElement: View) {

        replaceFragment(fragment, sharedElement)
    }

    override fun navigateToFragmentAndSaveState(currentFragment: Fragment, newFragment: Fragment, sharedElement: View) {
        addFragment(currentFragment, newFragment, sharedElement)
    }

    private fun addFragment(currentFragment: Fragment, newFragment: Fragment, sharedElement: View) {
        supportFragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(sharedElement, sharedElement.transitionName)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .hide(currentFragment)
                .add(R.id.fragment_container, newFragment, newFragment::class.simpleName)
                .addToBackStack(null)
                .commit()
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    private fun replaceFragment(fragment: Fragment, sharedElement: View, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(sharedElement, sharedElement.transitionName)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_container, fragment, fragment::class.simpleName)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }
}
