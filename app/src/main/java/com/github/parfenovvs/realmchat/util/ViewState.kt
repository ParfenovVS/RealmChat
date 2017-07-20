package com.github.parfenovvs.realmchat.util


sealed class ViewState<T> {
    class Loading<T> : ViewState<T>()
    class Result<T>(val result: T) : ViewState<T>()
    class Error<T>(val error: Throwable) : ViewState<T>()
}