package com.example.chai.cocktails.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.chai.cocktails.R;
import com.example.chai.cocktails.models.pojos.DrinkDetail;
import com.example.chai.cocktails.utils.Constants;
import com.example.chai.cocktails.utils.GlideApp;
import com.example.chai.cocktails.views.activities.DrinkFullDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrinkDetailListAdapter extends RecyclerView.Adapter<DrinkDetailListAdapter.DrinkDetailViewHolder> {

    List<DrinkDetail> drinks;
    Context context;

    public DrinkDetailListAdapter(List<DrinkDetail> drinks, Context context) {
        this.drinks = drinks;
        this.context = context;
    }

    @NonNull
    @Override
    public DrinkDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.detail_list_viewholder, parent, false);
        return new DrinkDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkDetailViewHolder holder, int position) {
        DrinkDetail drink = drinks.get(position);
        showLogo(drink.getStrDrinkThumb(), holder.detailedDrinkImage);
        holder.detailedDrinkName.setText(drink.getStrDrink());
    }


    private void showLogo(String url, ImageView imageView) {
        GlideApp
                .with(context)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    class DrinkDetailViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.detailedDrinkImage)
        ImageView detailedDrinkImage;

        @BindView(R.id.detailedDrinkName)
        TextView detailedDrinkName;

        @BindView(R.id.detailedCategoryItem)
        CardView detailedCategoryItem;

        public DrinkDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            detailedCategoryItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DrinkFullDetailActivity.class);
                    intent.putExtra(Constants.ID, drinks.get(getAdapterPosition()).getIdDrink());
                    context.startActivity(intent);
                }
            });
        }
    }
}
