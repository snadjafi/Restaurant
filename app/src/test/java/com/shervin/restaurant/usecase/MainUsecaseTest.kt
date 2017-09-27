package com.shervin.restaurant.usecase

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.shervin.restaurant.UnitTestingRxSchedulers
import com.shervin.restaurant.contract.data.RestaurantDataContract
import com.shervin.restaurant.data.Restaurant
import com.shervin.restaurant.data.RestaurantItemModel

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.util.ArrayList

import rx.Observable
import rx.observers.TestSubscriber

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.mockito.ArgumentMatchers.eq

@RunWith(MockitoJUnitRunner::class)
class MainUsecaseTest {

    @Mock private lateinit var mockRepository: RestaurantDataContract.Repository
    @Mock private lateinit var stubRestaurant: Restaurant
    @InjectMocks private lateinit var usecase: MainUsecase

    private lateinit var favorites: MutableList<Restaurant>
    private lateinit var restaurants: MutableList<Restaurant>

    private val lat = 37.422740
    private val lng = -122.139956

    @Before
    @Throws(Exception::class)
    fun setUp() {
        favorites = ArrayList(2)
        favorites.add(Restaurant())
        favorites.add(Restaurant())

        restaurants = ArrayList(4)
        restaurants.add(Restaurant())
        restaurants.add(Restaurant())
        restaurants.add(Restaurant())
        restaurants.add(Restaurant())

        usecase.setRxSchedulers(UnitTestingRxSchedulers())
    }

    @Test
    @Throws(Exception::class)
    fun fetchRestaurants() {
        val testSubscriber = TestSubscriber<List<RestaurantItemModel>>()

        whenever(mockRepository.fetch(lat, lng)).thenReturn(Observable.just(restaurants))
        whenever(mockRepository.favorites).thenReturn(Observable.just(favorites))

        usecase.fetchRestaurants(lat, lng, testSubscriber)
        val items = testSubscriber.onNextEvents[0]

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)

        assertEquals(6, items.size.toLong())
        assertTrue(items[0].isFavor)
        assertTrue(items[1].isFavor)
        assertFalse(items[2].isFavor)
        assertFalse(items[3].isFavor)
        assertFalse(items[4].isFavor)
        assertFalse(items[5].isFavor)
    }

    @Test
    @Throws(Exception::class)
    fun saveFavorite() {
        usecase.saveFavorite(stubRestaurant)

        verify(mockRepository).saveFavorite(eq(stubRestaurant))
    }
}