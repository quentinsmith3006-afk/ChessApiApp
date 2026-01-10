module com.github.quentinsmith3006afk.chessapiapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.quentinsmith3006afk.chessapiapp to javafx.fxml;
    exports com.github.quentinsmith3006afk.chessapiapp;
}