package com.sabekwla7ek.mooovies.vvm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.vvm.movieslist.MoviesListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), FragmentNavigator, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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
