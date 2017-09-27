package com.shervin.restaurant.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shervin.restaurant.GlideApp;
import com.shervin.restaurant.R;
import com.shervin.restaurant.contract.RestaurantDetailContract;
import com.shervin.restaurant.data.RestaurantDetail;

import javax.inject.Inject;

import butterknife.BindView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailContract.View {

    private static final String EXTRA_ID = "extra_id";

    @BindView(R.id.name) TextView name;
    @BindView(R.id.cover_iv) ImageView cover;
    @BindView(R.id.address) TextView address;
    @BindView(R.id.progress) ProgressBar progressBar;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.phone_number) TextView phoneNumber;

    @Inject RestaurantDetailContract.Presenter presenter;

    public static Intent newIntent(@NonNull Context context, int restaurantId) {
        return new Intent(context, RestaurantDetailActivity.class).putExtra(EXTRA_ID, restaurantId);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        presenter.fetchRestaurantDetail(getIntent().getIntExtra(EXTRA_ID, -1));
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {
        //todo handle error
    }

    @Override
    public void setDetail(@NonNull RestaurantDetail restaurantDetail) {
        name.setText(restaurantDetail.name);
        description.setText(restaurantDetail.description);
        phoneNumber.setText(restaurantDetail.phone_number);
        address.setText(restaurantDetail.address.shortname);

        GlideApp.with(this)
                .load(restaurantDetail.cover_img_url)
                .transition(withCrossFade())
                .into(cover);
    }
}
