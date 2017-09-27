package com.shervin.restaurant.page;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.shervin.restaurant.R;
import com.shervin.restaurant.ui.RestaurantDetailActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class RestaurantDetailPageObject {

    public RestaurantDetailPageObject checkDescriptionDisplayed() {
        onView(withId(R.id.description)).check(matches(isDisplayed()))
                .check(matches(withText("description")));
        return this;
    }

    public RestaurantDetailPageObject checkAddressDisplayed() {
        onView(withId(R.id.address)).check(matches(isDisplayed()))
                .check(matches(withText("1190 mission")));
        return this;
    }

    public RestaurantDetailPageObject checkNameDisplayed() {
        onView(withId(R.id.name)).check(matches(isDisplayed()))
                .check(matches(withText("name")));
        return this;
    }

    public RestaurantDetailPageObject checkPhoneDisplayed() {
        onView(withId(R.id.phone_number)).check(matches(isDisplayed()))
                .check(matches(withText("123")));
        return this;
    }

    public RestaurantDetailPageObject checkLoadingNotDisplayed() {
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())));
        return this;
    }

    public static RestaurantDetailPageObject launch(ActivityTestRule<RestaurantDetailActivity> activityTestRule,
                                                    Intent intent) {
        activityTestRule.launchActivity(intent);
        return new RestaurantDetailPageObject();
    }
}
