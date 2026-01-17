module com.github.quentinsmith3006afk.chessapiapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;
    requires qchess.chess;

    opens com.github.quentinsmith3006afk.chessapiapp;
    exports com.github.quentinsmith3006afk.chessapiapp;
}