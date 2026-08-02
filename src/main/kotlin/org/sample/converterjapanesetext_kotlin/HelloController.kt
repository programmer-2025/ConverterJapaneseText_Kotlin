package org.sample.converterjapanesetext_kotlin

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import java.net.URL

class HelloController {

    lateinit var beforeConvertField: TextField
    lateinit var afterConvertField: TextField

    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onClick() {
        if (beforeConvertField.text.isEmpty()) {

        }
        else {

        }
    }

    private fun convertText(text: String): String? {
        val url = URL("");
        return null;
    }
}