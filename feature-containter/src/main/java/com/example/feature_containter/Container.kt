package com.example.feature_containter

import kotlin.reflect.KClass

abstract class  BaseFeatureContainer {
    abstract fun defineModule(container: Container)
}

abstract class BaseFeature {
    val localContainer: Container = Container()
    abstract fun initModule(container: Container)
}

class Container {
    val features: MutableMap<KClass<out Any>, Any> = mutableMapOf()
}

fun Container.add(feature: Any) {
    features.put(feature::class, feature)
}

inline fun <reified F : Any> Container.get(): F? {
    return features[F::class] as? F
}