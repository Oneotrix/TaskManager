package com.dirion.walltechtodo.view.features.notification

import com.dirion.walltechtodo.R

data class NotificationModel(
    val notifications: List<SwitcherModel>
) {
    data class SwitcherModel(
        val type: SwitcherType,
        val isChecked: Boolean,
    ) {
        enum class SwitcherType {
            READY,
            STEADY,
            GO;

            companion object {
                fun typeTitle(type: SwitcherType) : String {
                    return when(type) {
                        READY -> "Ready"
                        STEADY -> "Steady"
                        GO -> "GO!"
                    }
                }

                fun activeTintTrackColor(type: SwitcherType) : Int {
                    return when(type) {
                        READY -> R.color.red
                        STEADY -> R.color.yellow
                        GO -> R.color.green
                    }
                }
            }
        }
    }
}
