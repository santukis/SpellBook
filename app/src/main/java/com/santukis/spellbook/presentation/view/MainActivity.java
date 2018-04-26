package com.santukis.spellbook.presentation.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;

import com.santukis.spellbook.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openView(new SpellsListFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try{
            getMenuInflater().inflate(R.menu.navigation, menu);
            return true;

        } catch (InflateException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_avatar_creation:
                openView(new AvatarCreationFragment());
                return true;
            case R.id.navigation_show_avatars:
                openView(new AvatarsListFragment());
                return true;
        }
        return false;
    }

    public void openView(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getTag())
                .commit();
    }

    public void closeView() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onBackPressed() {
        int LAST_FRAGMENT = 1;

        if(getFragmentManager().getBackStackEntryCount() == LAST_FRAGMENT) {
            finish();
        }

        super.onBackPressed();
    }
}
