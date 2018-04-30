package com.santukis.spellbook.presentation.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.santukis.spellbook.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewComponents();
        initializeViewListeners();

        openView(new SpellsListFragment());
    }

    private void initializeViewComponents() {
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_view);
        navigationDrawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    private void initializeViewListeners() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_avatar_creation:
                openView(new AvatarCreationFragment());
                break;
            case R.id.navigation_show_avatars:
                openView(new AvatarsListFragment());
                break;
            case R.id.navigation_show_spells:
                openView(new SpellsListFragment());
                break;
            case R.id.navigation_show_preferences:
                openView(new SettingsFragment());
                break;
            case R.id.navigation_show_about:
                openView(new AboutFragment());
        }
        navigationDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigationDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(int titleId) {
        getSupportActionBar().setTitle(titleId);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void openView(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getTag())
                .commit();
    }

    public void closeView() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onBackPressed() {
        if (closeNavigationDrawerIfOpened()) {
            return;

        } else if (closeAppIfLastFragment()) {
            finish();

        }

        super.onBackPressed();
    }

    private boolean closeNavigationDrawerIfOpened(){
        if(navigationDrawer.isDrawerOpen(GravityCompat.START)){
            navigationDrawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    private boolean closeAppIfLastFragment() {
        int LAST_FRAGMENT = 1;
        return getFragmentManager().getBackStackEntryCount() == LAST_FRAGMENT;
    }
}
