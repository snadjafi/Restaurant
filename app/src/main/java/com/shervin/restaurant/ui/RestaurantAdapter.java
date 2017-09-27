package com.shervin.restaurant.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shervin.restaurant.GlideApp;
import com.shervin.restaurant.R;
import com.shervin.restaurant.data.RestaurantItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<RestaurantItemModel> items = new ArrayList<>();
    @Nullable private ItemClickListener itemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurant, parent, false));

        holder.itemView.setOnClickListener(view -> {
            int position = holder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(items.get(position));
                }
            }
        });

        holder.favorite.setOnClickListener(view -> {
            int position = holder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (itemClickListener != null) {
                    items.get(position).isFavor = true;
                    notifyItemChanged(position);
                    itemClickListener.onFavoriteClick(items.get(position));
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(@NonNull List<RestaurantItemModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setItemClickListener(@Nullable ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(RestaurantItemModel restaurant);

        void onFavoriteClick(RestaurantItemModel restaurant);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.restaurant_name) TextView name;
        @BindView(R.id.restaurant_type) TextView type;
        @BindView(R.id.favorite_iv) ImageView favorite;
        @BindView(R.id.cover_iv) ImageView cover;
        @BindView(R.id.status) TextView status;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull RestaurantItemModel item) {
            name.setText(item.restaurant.name);
            type.setText(item.restaurant.description);
            status.setText(item.restaurant.status);
            favorite.setImageResource(item.isFavor ? R.drawable.ic_star_red_45dp : R.drawable.ic_star_border_red_45dp);
            GlideApp.with(cover)
                    .load(item.restaurant.cover_img_url)
                    .placeholder(R.color.colorAccent)
                    .transition(withCrossFade())
                    .into(cover);
        }
    }
}
