package Appl;

import Model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MP3 extends Application {
    private Map<String, MediaPlayer> mediaPlayers = new HashMap<>();
    private Song song1 = new Song("Songs/keshi.mp3", "Keshi ~ Always", "file:Images/keshi1.jpeg");
    private Song song2 = new Song("Songs/keshi2.mp3", "Keshi ~ BYS", "file:Images/keshi2.jpeg");
    private Song song3 = new Song("Songs/keshi3.mp3", "Keshi ~ Talk", "file:Images/keshi3.jpeg");
    private Song song4 = new Song("Songs/keshi4.mp3", "Keshi ~ Right Here", "file:Images/keshi4.jpeg");
    private Song song5 = new Song("Songs/magma.mp3", "Sewerperson ~ Magma", "file:Images/magma.jpeg");
    private Song currentSong = song1;

    private Button makeButton(String text, String path) {
        MediaPlayer player;
        if(mediaPlayers.containsKey(path) == false) {
            String uri = new File(path).toURI().toString();
            Media media = new Media(uri);
            player = new MediaPlayer(media);
            mediaPlayers.put(path, player);
        } else {
            player = mediaPlayers.get(path);
        }
        Button button = new Button(text);

        HBox.setHgrow(button, Priority.ALWAYS);
        VBox.setVgrow(button, Priority.ALWAYS);
        return button;
    }

    private HBox AlbumCover(Image image) {
        ImageView view = new ImageView(image);
        HBox box = new HBox(view);
        box.setAlignment(Pos.CENTER_LEFT);

        return box;
    }

    private HBox bottomButtons() {
        List<Button> buttons = new ArrayList<>();
        Button play = makeButton("▶", currentSong.getPath());
        play.setOnAction(event -> {
            currentSong.play();
        });
        Button pause = makeButton("||", currentSong.getPath());
        pause.setOnAction(event -> {
            currentSong.togglePause();
        });
        Button stop = makeButton("⬛", currentSong.getPath());
        stop.setOnAction(event -> {
            currentSong.stop();
        });
        Button forward = makeButton("▶▶", currentSong.getPath());
        forward.setOnAction(event -> {
            currentSong.forward();
        });
        Button rewind = makeButton("◀◀", currentSong.getPath());
        rewind.setOnAction(event -> {
            currentSong.rewind();
        });
        buttons.add(play); buttons.add(pause); buttons.add(stop); buttons.add(forward); buttons.add(rewind);
        for(Button button : buttons) {
            button.setAlignment(Pos.BOTTOM_CENTER);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(40);
        }

        HBox box = new HBox(rewind, pause, play, stop, forward);
        box.setAlignment(Pos.BOTTOM_CENTER);

        return box;
    }

    private VBox songButtons(Stage stage, BorderPane pane) {
        List<Button> buttons = new ArrayList<>();
        Button first = makeButton(song1.getDesc(), song1.getPath());
        first.setOnAction(event -> {
            currentSong.stop();
            song1.play();
            currentSong = song1;
            stage.setTitle(song1.getDesc());
            pane.setLeft(AlbumCover(song1.getImage()));
        });
        Button second = makeButton(song2.getDesc(), song2.getPath());
        second.setOnAction(event -> {
            currentSong.stop();
            song2.play();
            currentSong = song2;
            stage.setTitle(song2.getDesc());
            pane.setLeft(AlbumCover(song2.getImage()));
        });
        Button third = makeButton(song3.getDesc(), song3.getPath());
        third.setOnAction(event -> {
            currentSong.stop();
            song3.play();
            currentSong = song3;
            stage.setTitle(song3.getDesc());
            pane.setLeft(AlbumCover(song3.getImage()));
        });
        Button fourth = makeButton(song4.getDesc(), song4.getPath());
        fourth.setOnAction(event -> {
            currentSong.stop();
            song4.play();
            currentSong = song4;
            stage.setTitle(song4.getDesc());
            pane.setLeft(AlbumCover(song4.getImage()));
        });
        Button fifth = makeButton(song5.getDesc(), song5.getPath());
        fifth.setOnAction(event -> {
            currentSong.stop();
            song5.play();
            currentSong = song5;
            stage.setTitle(song5.getDesc());
            pane.setLeft(AlbumCover(song5.getImage()));
        });

        buttons.add(first); buttons.add(second); buttons.add(third); buttons.add(fourth); buttons.add(fifth);

        for(Button button : buttons) {
            button.setAlignment(Pos.CENTER_RIGHT);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            VBox.setVgrow(button, Priority.ALWAYS);
        } 

        VBox box = new VBox(first, second, third, fourth, fifth);
        box.setAlignment(Pos.CENTER_RIGHT);

        return box;
    }

    @Override
    public void start(Stage stage) {

        BorderPane pane = new BorderPane();
        pane.setRight(songButtons(stage, pane));
        pane.setLeft(AlbumCover(currentSong.getImage()));
        pane.setBottom(bottomButtons());

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Late Night");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
