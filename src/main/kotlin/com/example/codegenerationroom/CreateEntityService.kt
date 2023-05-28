package com.example.codegenerationroom

import com.intellij.openapi.components.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Service
@State(name = "CreateEntityData", storages = [Storage("CreateEntityData.xml")])
class CreateEntityService : PersistentStateComponent<CreateEntityService.State> {
    companion object {
        val instance: CreateEntityService
            get() = ServiceManager.getService(CreateEntityService::class.java)
    }

    var createEntityState = State()

    override fun getState(): State {
        return createEntityState
    }

    override fun loadState(state: State) {
        createEntityState = state
    }

    class State {
        var className: String by object : LoggingProperty<State, String>("") {}
        var attributeName1: String by object : LoggingProperty<State, String>("") {}
        var attributeName2: String by object : LoggingProperty<State, String>("") {}
        var attributeName3: String by object : LoggingProperty<State, String>("") {}
        var attributeName4: String by object : LoggingProperty<State, String>("") {}
        var attributeName5: String by object : LoggingProperty<State, String>("") {}
        var attributeType1: String by object : LoggingProperty<State, String>("") {}
        var attributeType2: String by object : LoggingProperty<State, String>("") {}
        var attributeType3: String by object : LoggingProperty<State, String>("") {}
        var attributeType4: String by object : LoggingProperty<State, String>("") {}
        var attributeType5: String by object : LoggingProperty<State, String>("") {}

        open class LoggingProperty<R, T>(initValue: T) : ReadWriteProperty<R, T> {
            private var backingField: T = initValue

            override operator fun getValue(thisRef: R, property: KProperty<*>): T {
                return backingField
            }

            override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
                backingField = value
            }
        }
    }
}