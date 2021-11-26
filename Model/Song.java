package Model;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class Song {
    private final MediaPlayer player;
    private final String description;
    private final Image image;
    private String path;
    private final static Duration skip = new Duration(10000);
    
    public Song(String mediaPath, String description, String imagePath) {
        Media media = new Media(new File(mediaPath).toURI().toString());
        this.path = mediaPath;
        this.player = new MediaPlayer(media);
        this.image = new Image(imagePath);
        this.description = description;
    }

    public String getDesc() {return description;}
    public Image getImage() {return image;}
    public Status getStatus() {return player.getStatus();}
    public String getPath() {return path;}

    public void play() {
        if(player.getStatus() != Status.PLAYING)
            player.play();
    }

    public void togglePause() {
        if(player.getStatus() == Status.PLAYING)
            player.pause();
        
        if(player.getStatus() == Status.PAUSED)
            player.play();
    }

    public void rewind() {
        player.seek(player.getCurrentTime().subtract(skip));
    }

    public void forward() {
        player.seek(player.getCurrentTime().add(skip));
    }

    public void stop() {
        player.stop();
    }
}