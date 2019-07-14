package id.ac.akakom.pkm.simpeldawis.menu.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.MenuItem
import id.ac.akakom.pkm.simpeldawis.R
import id.ac.akakom.pkm.simpeldawis.base.BaseActivity
import id.ac.akakom.pkm.simpeldawis.menu.akun.AkunActivity
import id.ac.akakom.pkm.simpeldawis.menu.notifikasi.NotifikasiActivity
import id.ac.akakom.pkm.simpeldawis.menu.sop.SopActivity
import kotlinx.android.synthetic.main.activity_main.*
import lib.almuwahhid.utils.BottomNavDisable

class Main : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    val TAG = "MainActivity"
    internal var fragment: Fragment? = null
    internal var active_fragment = 0
    internal var after_active_fragment = 0

    internal var viewNotifikasi = NotifikasiActivity.newInstance()
    internal var viewSop = SopActivity.newInstance()
    internal var viewAkun = AkunActivity.newInstance()
    internal var mFragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if(!Function.checkUserPreference(context)){
//            finish()
//            startActivity(Intent(context, LoginActivity::class.java))
//        }

        BottomNavDisable.disableShiftMode(nav)
        if (savedInstanceState != null) {
            fragment = supportFragmentManager.getFragment(savedInstanceState, "fragment")
        } else {
            fragment = viewNotifikasi
        }

        initializeNavFragment(fragment!!)
        nav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onNavigationItemSelected: " + item.getItemId())
        when (item.getItemId()) {
            R.id.nav_notifikasi -> {
                initActiveFragment(0)
                fragment = viewNotifikasi
            }
            R.id.nav_sop -> {
                initActiveFragment(1)
                fragment = viewSop
            }
            R.id.nav_akun -> {
                initActiveFragment(2)
                fragment = viewAkun
            }
        }
        initializeNavFragment(fragment!!)
        return true
    }

    private fun initializeNavFragment(curFragment: Fragment) {
        val transaction = mFragmentManager.beginTransaction()

        if (mFragmentManager.findFragmentByTag(curFragment.javaClass.simpleName) == null) {
            transaction.add(R.id.contentContainer, curFragment, curFragment.javaClass.simpleName)
        }

        val tagNotif = mFragmentManager.findFragmentByTag(NotifikasiActivity.javaClass.getSimpleName())
        val tagSop = mFragmentManager.findFragmentByTag(SopActivity.javaClass.getSimpleName())
        val tagProfil = mFragmentManager.findFragmentByTag(AkunActivity.javaClass.getSimpleName())
//        hideFragment(transaction, tagNotif, tagSop, tagProfil)
        showFragment(curFragment, transaction, tagNotif, tagSop, tagProfil)

        after_active_fragment = active_fragment
        transaction.commitAllowingStateLoss()
    }

    private fun initActiveFragment(a: Int) {
        active_fragment = a
    }

    private fun showFragment(curFragment: Fragment, transaction: FragmentTransaction, tagMain: Fragment?, tagSop: Fragment?, tagProfil: Fragment?) {
        if (curFragment.javaClass.simpleName == NotifikasiActivity.javaClass.getSimpleName()) {
            if (tagMain != null) {
                transaction.show(tagMain)
            }
        }

        if (curFragment.javaClass.simpleName == SopActivity.javaClass.getSimpleName()) {
            if (tagSop != null) {
                transaction.show(tagSop)
            }
        }

        if (curFragment.javaClass.simpleName == AkunActivity.javaClass.getSimpleName()) {
            if (tagProfil != null) {
                transaction.show(tagProfil)
            }
        }
    }

    private fun hideFragment(transaction: FragmentTransaction, tagMain: Fragment, tagSop: Fragment, tagProfil: Fragment) {
        if (tagMain != null) {
            //            initAnimNav(transaction);
            transaction.hide(tagMain)
        }

        if (tagSop != null) {
//            initAnimNav(transaction);
            transaction.hide(tagSop);
        }

        if (tagProfil != null) {
            //            initAnimNav(transaction);
            transaction.hide(tagProfil)
        }
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Save the fragment's instance
        if (fragment!!.isAdded()) {
            supportFragmentManager.putFragment(outState, "fragment", fragment!!)
        }
    }
}
