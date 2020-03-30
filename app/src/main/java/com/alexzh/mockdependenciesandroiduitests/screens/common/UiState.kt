package com.alexzh.mockdependenciesandroiduitests.screens.common

sealed class UiState<out T> {
    data class Success<out T>(val data: T): UiState<T>()
    data class Error(val throwable: Throwable): UiState<Nothing>()
}