package com.shervin.restaurant.data;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Restaurant implements RealmModel {
    @PrimaryKey public int id;
    public String name;
    public String description;
    public String cover_img_url;
    public String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
