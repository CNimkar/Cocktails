package com.example.chai.cocktails.views.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.chai.cocktails.R;
import com.example.chai.cocktails.models.apiResponseWrappers.DrinkFullDetailsAPIResponse;
import com.example.chai.cocktails.models.pojos.DrinkFullDetail;
import com.example.chai.cocktails.utils.Constants;
import com.example.chai.cocktails.utils.GlideApp;
import com.example.chai.cocktails.viewModels.DrinkFullDetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrinkFullDetailActivity extends AppCompatActivity {

    DrinkFullDetailViewModel viewModel;
    String id;


    @BindView(R.id.fullDetailImage)
    ImageView fullDetailImage;

    @BindView(R.id.fullDetailTitle)
    TextView fullDetailTitle;

    @BindView(R.id.fullDetailVideoLink)
    TextView fullDetailVideoLink;

    @BindView(R.id.fullDetailCategory)
    TextView fullDetailCategory;

    @BindView(R.id.fullDetailGlass)
    TextView fullDetailGlass;


    @BindView(R.id.fullDetailAlcoholic)
    TextView fullDetailAlcoholic;

    @BindView(R.id.fullDetailDescription)
    TextView fullDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_full_detail);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this)
                .get(DrinkFullDetailViewModel.class);
        fetchId();
        subscribeToResponseObserver();
    }

    private void fetchId() {
        if (getIntent() != null) {
            id = getIntent().getStringExtra(Constants.ID);
        }
    }

    private void subscribeToResponseObserver() {
        if (id != null) {
            viewModel.getApiResponse(id).observe(this,
                    new Observer<DrinkFullDetailsAPIResponse>() {
                        @Override
                        public void onChanged(@Nullable DrinkFullDetailsAPIResponse
                                                      drinkFullDetailsAPIResponse) {
                            switch (drinkFullDetailsAPIResponse.
                                    getResponseType()) {
                                case DrinkFullDetailsAPIResponse.SUCCESSFUL_RESPONSE:
                                    loadData(drinkFullDetailsAPIResponse.getDrink());
                                    break;
                                case DrinkFullDetailsAPIResponse.REQUEST_ERROR_RESPONSE:
                                case DrinkFullDetailsAPIResponse.THROWABLE_ERROR_RESPONSE:
                                    displayNetworkingErrorToast();
                            }
                        }
                    });
        }

    }

    private void displayNetworkingErrorToast() {
        Toast.makeText(this,
                "Unable to get details",
                Toast.LENGTH_LONG).show();
    }


    private void loadData(DrinkFullDetail drink) {
        showLogo(drink.getThumbnail(), fullDetailImage);
        fullDetailTitle.setText(drink.getName());
        if (drink.getVideoLink() == null || drink.getVideoLink().equals("")) {
            fullDetailVideoLink.setVisibility(View.GONE);
        } else {
            fullDetailVideoLink.setText(drink.getVideoLink());
        }

        fullDetailCategory.setText(drink.getCategory());
        fullDetailGlass.setText(drink.getGlassType());
        fullDetailAlcoholic.setText(drink.getAlcoholic());
        fullDetailDescription.setText(drink.getInstructions());
    }


    private void showLogo(String url, ImageView imageView) {
        GlideApp
                .with(this)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }


}
