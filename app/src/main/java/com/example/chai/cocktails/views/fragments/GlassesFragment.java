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
import android.widget.Toast;

import com.example.chai.cocktails.R;
import com.example.chai.cocktails.adapters.CategoryListAdapter;
import com.example.chai.cocktails.models.apiResponseWrappers.NameListingAPIResponse;
import com.example.chai.cocktails.models.pojos.Drink;
import com.example.chai.cocktails.utils.Constants;
import com.example.chai.cocktails.viewModels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GlassesFragment extends Fragment {

    CategoryListAdapter adapter;
    MainViewModel mainViewModel;
    List<Drink> drinksList;

    @BindView(R.id.categoryList)
    RecyclerView categoryList;
    private OnFragmentInteractionListener mListener;

    public GlassesFragment() {
    }

    @Override
    public String toString() {
        return Constants.FILTER_GLASS;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);
        mainViewModel = ViewModelProviders.of((AppCompatActivity) getActivity())
                .get(MainViewModel.class);

        drinksList = new ArrayList<Drink>();
        initAdapterWithNoData();
        subscribeToResponseObserver();
        mainViewModel.getData(this.toString());
        return view;

    }

    private void initAdapterWithNoData() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        categoryList.setLayoutManager(layoutManager);
        adapter =
                new CategoryListAdapter(drinksList, getActivity(), this.toString());
        categoryList.setAdapter(adapter);
    }

    private void loadDataWithSubscription(List<Drink> categories) {
        drinksList.clear();
        drinksList.addAll(categories);
        adapter.notifyDataSetChanged();
    }

    private void subscribeToResponseObserver() {
        mainViewModel.getApiResponse(this.toString())
                .observe((AppCompatActivity) getActivity(), new Observer<NameListingAPIResponse>() {
                    @Override
                    public void onChanged(@Nullable NameListingAPIResponse nameListingAPIResponse) {
                        switch (nameListingAPIResponse.getResponseType()) {
                            case NameListingAPIResponse.SUCCESSFUL_RESPONSE:
                                loadDataWithSubscription(nameListingAPIResponse.getDrinks());
                                break;
                            case NameListingAPIResponse.REQUEST_ERROR_RESPONSE:
                            case NameListingAPIResponse.THROWABLE_ERROR_RESPONSE:
                                displayNetworkingErrorToast();
                        }
                    }
                });
    }

    private void displayNetworkingErrorToast() {
        Toast.makeText(getActivity(),
                "Unable to get the list",
                Toast.LENGTH_LONG).show();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
