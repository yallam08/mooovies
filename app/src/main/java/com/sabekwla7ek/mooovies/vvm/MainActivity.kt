package com.sabekwla7ek.mooovies.vvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.vvm.movieslist.MoviesListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), FragmentNavigator, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(MoviesListFragment(), addToBackStack = false)
    }

    override fun navigateToFragment(fragment: androidx.fragment.app.Fragment, sharedElement: View) {

        replaceFragment(fragment, sharedElement)
    }

    override fun navigateToFragmentAndSaveState(currentFragment: androidx.fragment.app.Fragment, newFragment: androidx.fragment.app.Fragment, sharedElement: View) {
        addFragment(currentFragment, newFragment, sharedElement)
    }

    private fun addFragment(currentFragment: androidx.fragment.app.Fragment, newFragment: androidx.fragment.app.Fragment, sharedElement: View) {
        supportFragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(sharedElement, sharedElement.transitionName)
                .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .hide(currentFragment)
                .add(R.id.fragment_container, newFragment, newFragment::class.simpleName)
                .addToBackStack(null)
                .commit()
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment, sharedElement: View, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(sharedElement, sharedElement.transitionName)
                .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_container, fragment, fragment::class.simpleName)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }
}
