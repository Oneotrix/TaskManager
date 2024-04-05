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
                fun titleByType(type: SwitcherType) : String {
                    return when(type) {
                        READY -> "Ready"
                        STEADY -> "Steady"
                        GO -> "GO!"
                    }
                }

                fun typeByTitle(title: String) : SwitcherType {
                    return when(title) {
                        "Ready" -> READY
                        "Steady" -> STEADY
                        "GO!" -> GO
                        else -> error("Unexpected title")
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
