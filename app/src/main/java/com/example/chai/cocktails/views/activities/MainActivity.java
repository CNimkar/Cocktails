package com.example.chai.cocktails.views.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.chai.cocktails.R;
import com.example.chai.cocktails.viewModels.MainViewModel;
import com.example.chai.cocktails.views.fragments.CategoriesFragment;
import com.example.chai.cocktails.views.fragments.GlassesFragment;
import com.example.chai.cocktails.views.fragments.IngredientFragment;

public class MainActivity extends AppCompatActivity
        implements GlassesFragment.OnFragmentInteractionListener,
        CategoriesFragment.OnFragmentInteractionListener,
        IngredientFragment.OnFragmentInteractionListener {

    MainViewModel mainViewModel;
    private TextView mTextMessage;
    private Fragment categoriesFragment;
    private Fragment glassesFragment;
    private Fragment ingredientFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(categoriesFragment);
                    return true;
                case R.id.navigation_dashboard:
                    setFragment(glassesFragment);
                    return true;
                case R.id.navigation_notifications:
                    setFragment(ingredientFragment);
                    return true;
            }
            return false;
        }
    };

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, fragment);
        transaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        categoriesFragment = new CategoriesFragment();
        glassesFragment = new GlassesFragment();
        ingredientFragment = new IngredientFragment();

        mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setFragment(categoriesFragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
