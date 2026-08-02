package org.sample.converterjapanesetext_kotlin

import com.google.gson.Gson
import com.google.gson.JsonArray
import org.apache.commons.lang3.StringUtils
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection

class HelloController {

    companion object {
        val kana_to_hiraMap: Map<String, String> = mapOf(
            ///
            "a"  to "あ", "i"  to "い", "u"  to "う", "e"  to "え", "o"  to "お",
            "ka" to "か", "ki" to "き", "ku" to "く", "ke" to "け", "ko" to "こ",
            "sa" to "さ", "si" to "し", "su" to "す", "se" to "せ", "so" to "そ",
            "ta" to "た", "ti" to "ち", "tu" to "つ", "te" to "て", "to" to "と",
            "na" to "な", "ni" to "に", "nu" to "ぬ", "ne" to "ね", "no" to "の",
            "ha" to "は", "hi" to "ひ", "hu" to "ふ", "he" to "へ", "ho" to "ほ",
            "ma" to "ま", "mi" to "み", "mu" to "む", "me" to "め", "mo" to "も",
            "ya" to "や",               "yu" to "ゆ",               "yo" to "よ",
            "wa" to "わ",               "wo" to "を",               "n" to "ん",
            ///
            "ga" to "が", "gi" to "ぎ", "gu" to "ぐ", "ge" to "げ", "go" to "ご",
            "za" to "ざ", "zi" to "じ", "zu" to "ず", "ze" to "ぜ", "zo" to "ぞ",
            "da" to "だ", "di" to "ぢ", "du" to "づ", "de" to "で", "do" to "ど",
            "pa" to "ぱ", "pi" to "ぴ", "pu" to "ぷ", "pe" to "ぺ", "po" to "ぽ",
        );

    }

    lateinit var beforeConvertField: TextField
    lateinit var afterConvertField: TextField

    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onClick() {
        if (beforeConvertField.text.isEmpty()) {

        }
        else {
            afterConvertField.text = convertText(beforeConvertField.text);
        }
    }

    private fun convertText(text: String, index: Int = 0): String? {
        val newText = StringUtils.replaceEach(text, kana_to_hiraMap.keys.toTypedArray(), kana_to_hiraMap.values.toTypedArray());
        val url = URL("https://www.google.com/transliterate?langpair=ja-Hira%7Cja&text=$newText");
        val connection = url.openConnection() as HttpsURLConnection;
        connection.requestMethod = "GET";
        val bufferReader = BufferedReader(InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        val builder = StringBuilder();
        var line = bufferReader.readLine()
        while (line != null) {
            builder.append(line);
            line = bufferReader.readLine()
        }

        val gson = Gson();
        val rootArray = gson.fromJson(builder.toString(), JsonArray::class.java);
        return rootArray.get(0).asJsonArray.get(1).asJsonArray.get(index).asString;
    }


}