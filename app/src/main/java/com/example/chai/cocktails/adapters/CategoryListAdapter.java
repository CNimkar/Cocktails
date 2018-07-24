package com.example.chai.cocktails.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chai.cocktails.R;
import com.example.chai.cocktails.models.pojos.Drink;
import com.example.chai.cocktails.utils.Constants;
import com.example.chai.cocktails.views.activities.DrinkDetailListingActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListAdapter extends
        RecyclerView.Adapter<CategoryListAdapter.CategoryItemViewHolder> {

    List<Drink> categories;
    Context context;
    String type;

    public CategoryListAdapter(List<Drink> categories, Context context, String type) {
        this.categories = categories;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.catagories_list_holder, parent, false);
        return new CategoryItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder,
                                 int position) {
        holder.categoryName.setText(categories.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.categoryName)
        TextView categoryName;

        @BindView(R.id.categoryItem)
        CardView categoryItem;

        public CategoryItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            categoryItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DrinkDetailListingActivity.class);
                    intent.putExtra(Constants.CATEGORY_NAME, categories.get(getAdapterPosition()).getCategory());
                    intent.putExtra(Constants.TYPE, type);
                    context.startActivity(intent);
                }
            });
        }
    }
}
