package com.e_commerce;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import static com.e_commerce.RegisterActivity.setSignUpFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int WISHLIST_FRAGMENT = 3;
    private static final int REWARDS_FRAGMENT = 4;
    private static final int ACCOUNT_FRAGMENT = 5;
    public static Boolean showCart = false;

    private FrameLayout frameLayout;
    private ImageView actionBarLogo;
    private int currentFragment = -1;
    private NavigationView navigationView;

    private Window window;
    private Toolbar toolbar;



    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.actionbar_logo);
        setSupportActionBar(toolbar);
        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setCheckable(true);

        frameLayout = findViewById(R.id.main_framelayout);

        if (showCart){
            drawer.setDrawerLockMode(1);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            gotoFragment("My Cart",new MyCartFragment(), -2);
        } else {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == HOME_FRAGMENT){
                currentFragment = -1;
                super.onBackPressed();
            } else {
                if (showCart){
                    showCart = false;
                    finish();
                }else {
                    actionBarLogo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new HomeFragment(), HOME_FRAGMENT);
                    navigationView.getMenu().getItem(0).setCheckable(true);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == HOME_FRAGMENT){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.main_search_icon){

            return true;
        } else if (id == R.id.main_notification_icon){

            return true;
        } else if (id == R.id.main_cart_icon){

            final Dialog signInDialog = new Dialog(MainActivity.this);
            signInDialog.setContentView(R.layout.sign_in_dialog);
            signInDialog.setCancelable(true);
            signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            Button dialogSignInBtn = signInDialog.findViewById(R.id.sign_in_btn);
            Button dialogSignUpBtn = signInDialog.findViewById(R.id.sign_up_btn);
            final Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);

            dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signInDialog.dismiss();
                    setSignUpFragment = false;
                    startActivity(registerIntent);

                }
            });

            dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signInDialog.dismiss();
                    setSignUpFragment = true;
                    startActivity(registerIntent);

                }
            });
            signInDialog.show();

            //gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
            return true;
        } else if (id == android.R.id.home){
            if (showCart){
                showCart = false;
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);
        if (fragmentNo == CART_FRAGMENT){
            navigationView.getMenu().getItem(3).setCheckable(true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_mall){
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }
        else if (id == R.id.nav_orders){
            gotoFragment("My Orders", new MyOrdersFragment(), ORDERS_FRAGMENT);
        } else if (id == R.id.nav_rewards){
            gotoFragment("My Rewards", new MyRewardsFragment(), REWARDS_FRAGMENT);
        }else if (id == R.id.nav_cart){
            gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
        } else if (id == R.id.nav_wishlist){
            gotoFragment("My Wishlist", new MyWishlistFragment(), WISHLIST_FRAGMENT);
        }else if (id == R.id.nav_account){
            gotoFragment("My Account", new MyAccountFragment(), ACCOUNT_FRAGMENT);
        } else if (id == R.id.nav_sign_out){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setFragment(Fragment fragment, int fragmentNo){
        if (fragmentNo != currentFragment){
            if (fragmentNo == REWARDS_FRAGMENT){
                window.setStatusBarColor(Color.parseColor("#5B04B1"));
                toolbar.setBackgroundColor(Color.parseColor("#5B04B1"));
            } else {
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }

    }


}
