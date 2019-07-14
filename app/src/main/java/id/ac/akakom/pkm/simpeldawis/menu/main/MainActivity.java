package id.ac.akakom.pkm.simpeldawis.menu.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.akakom.pkm.simpeldawis.R;
import id.ac.akakom.pkm.simpeldawis.base.BaseActivity;
import id.ac.akakom.pkm.simpeldawis.menu.akun.AkunActivity;
import id.ac.akakom.pkm.simpeldawis.menu.login.LoginActivity;
import id.ac.akakom.pkm.simpeldawis.menu.notifikasi.NotifikasiActivity;
import id.ac.akakom.pkm.simpeldawis.menu.sop.SopActivity;
import id.ac.akakom.pkm.simpeldawis.utils.Function;
import lib.almuwahhid.utils.BottomNavDisable;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    String TAG = "MainActivity";

    @BindView(R.id.nav) protected BottomNavigationView navigation;
    Fragment fragment = null;
    int active_fragment = 0;
    int after_active_fragment = 0;

    Fragment viewNotifikasi = NotifikasiActivity.newInstance();
    Fragment viewSop = SopActivity.newInstance();
    Fragment viewAkun = AkunActivity.newInstance();
    FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_notifikasi:
                initActiveFragment(0);
                fragment = viewNotifikasi;
                break;
            case R.id.nav_sop:
                initActiveFragment(1);
                fragment = viewSop;
                break;
            case R.id.nav_akun:
                initActiveFragment(2);
                fragment = viewAkun;
                break;
        }
        initializeNavFragment(fragment);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(!Function.checkUserPreference(getContext())){
            finish();
            startActivity(new Intent(getContext(), LoginActivity.class));
        }

//        if(!Function.checkUserPreference(context)){
//            finish()
//            startActivity(Intent(context, LoginActivity::class.java))
//        }

        BottomNavDisable.disableShiftMode(navigation);
        if (savedInstanceState != null) {
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "fragment");
        } else {
            fragment = viewNotifikasi;
        }

        initializeNavFragment(fragment);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void initializeNavFragment(Fragment curFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(curFragment.getClass().getSimpleName()) == null) {
            transaction.add(R.id.contentContainer, curFragment, curFragment.getClass().getSimpleName());
        }

        Fragment tagMain = mFragmentManager.findFragmentByTag(viewNotifikasi.getClass().getSimpleName());
        Fragment tagSop = mFragmentManager.findFragmentByTag(viewSop.getClass().getSimpleName());
        Fragment tagProfil = mFragmentManager.findFragmentByTag(viewAkun.getClass().getSimpleName());
        hideFragment(transaction, tagMain, tagSop, tagProfil);
        showFragment(curFragment, transaction, tagMain, tagSop, tagProfil);

        after_active_fragment = active_fragment;
        transaction.commitAllowingStateLoss();
    }

    private void initActiveFragment(int a){
        active_fragment = a;
    }

    private void showFragment(Fragment curFragment, FragmentTransaction transaction, Fragment tagMain, Fragment tagSop, Fragment tagProfil) {
        if (curFragment.getClass().getSimpleName().equals(viewNotifikasi.getClass().getSimpleName())) {
            if (tagMain != null) {
                transaction.show(tagMain);
            }
        }

        if (curFragment.getClass().getSimpleName().equals(viewSop.getClass().getSimpleName())) {
            if (tagSop != null) {
                transaction.show(tagSop);
            }
        }


        /*if (curFragment.getClass().getSimpleName().equals(wisataFragment.getClass().getSimpleName())) {
            if (tagWisata != null) {
                transaction.show(tagWisata);
            }
        }*/

        if (curFragment.getClass().getSimpleName().equals(viewAkun.getClass().getSimpleName())) {
            if (tagProfil != null) {
                transaction.show(tagProfil);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if(LibUi.getSPString(this, Preferences.SERVER).equals("")){
            new DialogSetServer(getContext());
        }*/
    }

    private void hideFragment(FragmentTransaction transaction, Fragment tagMain, Fragment tagSop, Fragment tagProfil) {
        if (tagMain != null) {
//            initAnimNav(transaction);
            transaction.hide(tagMain);
        }

        if (tagSop != null) {
//            initAnimNav(transaction);
            transaction.hide(tagSop);
        }

        if (tagProfil != null) {
//            initAnimNav(transaction);
            transaction.hide(tagProfil);
        }
    }
}
