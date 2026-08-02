module org.sample.converterjapanesetext_kotlin {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens org.sample.converterjapanesetext_kotlin to javafx.fxml;
    exports org.sample.converterjapanesetext_kotlin;
}