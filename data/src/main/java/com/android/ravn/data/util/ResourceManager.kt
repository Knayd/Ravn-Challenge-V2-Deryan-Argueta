package com.android.ravn.data.util

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val resources = context.resources

    fun getString(@StringRes res: Int) = resources.getString(res)

    fun getString(@StringRes res: Int, vararg formatArgs: Any) =
        resources.getString(res, *formatArgs)

    fun getQuantityString(@PluralsRes res: Int, quantity: Int, vararg formatArgs: Any) =
        resources.getQuantityString(res, quantity, *formatArgs)

    fun getDrawable(@DrawableRes res: Int) = ResourcesCompat.getDrawable(resources, res, null)
}