package io.collective

import java.time.Clock

class SimpleAgedKache {
    private val cache = mutableMapOf<Any, Any>()
    private val age = mutableMapOf<Any, Long>()

    constructor(clock: Clock?) {
        cache = mutableMapOf()
        age = mutableMapOf()
    }

    constructor() {
        cache = mutableMapOf()
        age = mutableMapOf()
    }

    fun put(key: Any?, value: Any?, retentionInMillis: Int) {
        cache[key] = value
        age[key] = System.currentTimeMillis() + retentionInMillis
    }

    fun isEmpty(): Boolean {

        return age.isEmpty()
    }

    fun size(): Int {
        return 0
    }

    fun get(key: Any?): Any? {
        return null
    }
}
