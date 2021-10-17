package com.android.ravn.dargueta.ui.navparams

import android.os.Bundle
import androidx.navigation.NavType
import com.android.ravn.domain.model.Person
import com.google.gson.Gson

class PersonParamType : NavType<Person>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): Person? = bundle.getParcelable(key)

    override fun parseValue(value: String): Person = Gson().fromJson(value, Person::class.java)

    override fun put(bundle: Bundle, key: String, value: Person) {
        bundle.putParcelable(key, value)
    }
}