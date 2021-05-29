package com.android.ravn.data.util.mapper.base

interface DomainMapper<T> {
    fun toDomainModel(): T
}
