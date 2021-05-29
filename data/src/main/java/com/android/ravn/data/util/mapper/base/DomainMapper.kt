package com.android.ravn.data.util.mapper.base

interface DomainMapper<T> {
    abstract fun toDomainModel(): T
}
