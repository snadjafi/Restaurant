package com.shervin.restaurant.presenter

import android.arch.lifecycle.Lifecycle
import com.nhaarman.mockito_kotlin.verify

import com.shervin.restaurant.contract.MainContract
import com.shervin.restaurant.data.Restaurant
import com.shervin.restaurant.data.RestaurantItemModel

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import rx.Subscriber

import org.mockito.ArgumentMatchers.eq


@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock private lateinit var mockUsecase: MainContract.Usecase
    @Mock private lateinit var mockView: MainContract.View
    @Mock private lateinit var mockLifecycle: Lifecycle
    @Mock private lateinit var stubThrowable: Throwable
    @Mock private lateinit var stubRestaurant: Restaurant
    @Mock private lateinit var stubItems: List<RestaurantItemModel>
    @InjectMocks private lateinit var presenter: MainPresenter
    @Captor private lateinit var captor: ArgumentCaptor<Subscriber<List<RestaurantItemModel>>>

    private val lat = 37.422740
    private val lng = -122.139956

    @Test
    @Throws(Exception::class)
    fun onDestroy() {
        presenter.onDestroy()

        verify(mockUsecase).clear()
        verify(mockLifecycle).removeObserver(eq(presenter))
    }

    @Suppress("FunctionName")
    @Test
    @Throws(Exception::class)
    fun fetchRestaurants_ShowLoading() {
        presenter.fetchRestaurants(lat, lng)

        verify(mockView).showLoading()
    }

    @Suppress("FunctionName")
    @Test
    @Throws(Exception::class)
    fun fetchRestaurants_OnSuccess() {
        presenter.fetchRestaurants(lat, lng)

        verify(mockUsecase).fetchRestaurants(eq(lat), eq(lng), captor.capture())
        captor.value.onNext(stubItems)

        verify(mockView).hideLoading()
        verify(mockView).setRestaurants(eq<List<RestaurantItemModel>>(stubItems))
    }

    @Suppress("FunctionName")
    @Test
    @Throws(Exception::class)
    fun fetchRestaurants_OnError() {
        presenter.fetchRestaurants(lat, lng)

        verify(mockUsecase).fetchRestaurants(eq(lat), eq(lng), captor.capture())
        captor.value.onError(stubThrowable)

        verify(mockView).hideLoading()
        verify(mockView).showError(eq<Throwable>(stubThrowable))
    }

    @Test
    @Throws(Exception::class)
    fun onFavoriteClick() {
        presenter.onFavoriteClick(stubRestaurant)

        verify(mockUsecase).saveFavorite(eq(stubRestaurant))
    }
}