package com.example.chai.cocktails.views.fragments;

import android.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chai.cocktails.R;
import com.example.chai.cocktails.adapters.CategoryListAdapter;
import com.example.chai.cocktails.models.Drink;
import com.example.chai.cocktails.utils.Constants;
import com.example.chai.cocktails.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientFragment extends Fragment {

    MainViewModel mainViewModel;
    CategoryListAdapter adapter;
    @BindView(R.id.categoryList)
    RecyclerView categoryList;
    private OnFragmentInteractionListener mListener;
    private List<Drink> categories;

    public IngredientFragment() {
    }

    @Override
    public String toString() {
        return Constants.FILTER_INGREDIENTS;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);
        categories = new ArrayList<>();
        mainViewModel = ViewModelProviders.of((AppCompatActivity) getActivity())
                .get(MainViewModel.class);

        loadData();
        initViewModel();

        mainViewModel.getData(this.toString());
        return view;

    }

    private void initViewModel() {
        mainViewModel.ingredientObservable.observe((AppCompatActivity) getActivity(),
                new Observer<List<Drink>>() {
                    @Override
                    public void onChanged(@Nullable List<Drink> drinks) {
                        categories.clear();
                        categories.addAll(drinks);
                        if (adapter == null) {
                            loadData();
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void loadData() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        categoryList.setLayoutManager(layoutManager);
        adapter =
                new CategoryListAdapter(categories, getActivity(), this.toString());
        categoryList.setAdapter(adapter);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
