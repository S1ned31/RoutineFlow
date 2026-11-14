package com.busol.habittracker.core.ui.components

data class ErrorData(
    val title: String,
    val subtitle: String? = null,
    val button: ButtonData? = null,
) {

    constructor(title: String, subtitle: String?, buttonText: String, action: String) : this(
        title = title,
        subtitle = subtitle,
        button = ButtonData(text = buttonText, action = action)
    )

    data class ButtonData(
        val text: String,
        val action: String
    )

}
