package com.shervin.restaurant.ui;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.shervin.restaurant.contract.data.RestaurantDataContract;
import com.shervin.restaurant.data.Address;
import com.shervin.restaurant.data.RestaurantDetail;
import com.shervin.restaurant.page.RestaurantDetailPageObject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observable;

import static org.mockito.Mockito.when;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RestaurantDetailActivityTest extends InstrumentationActivityTest {

    @Rule
    public ActivityTestRule<RestaurantDetailActivity> activityTestRule =
            new ActivityTestRule<>(RestaurantDetailActivity.class, true, false);
    private RestaurantDataContract.Repository repository;
    int id = 30;

    @Before
    public void setUp() throws Exception {
        this.repository = getAppComponent().restaurantRepository();
        given();
    }

    @Test
    public void checkEverythingIsDisplayed() {
        Intent intent = new Intent();
        intent.putExtra("extra_id", id);
        RestaurantDetailPageObject.launch(activityTestRule, intent)
                .checkDescriptionDisplayed()
                .checkAddressDisplayed()
                .checkNameDisplayed()
                .checkPhoneDisplayed()
                .checkLoadingNotDisplayed();
    }

    private void given() {
        RestaurantDetail restaurant = new RestaurantDetail();
        Address address = new Address();
        address.shortname = "1190 mission";
        restaurant.address = address;
        restaurant.cover_img_url = "https://cdn.doordash.com/media/restaurant/cover/Amarin-Thai-Cuisine.png";
        restaurant.description = "description";
        restaurant.phone_number = "123";
        restaurant.name = "name";
        restaurant.id = id;

        when(repository.fetch(id)).thenReturn(Observable.just(restaurant));
    }
}