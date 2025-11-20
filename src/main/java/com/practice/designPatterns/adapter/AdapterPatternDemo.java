package com.practice.designPatterns.adapter;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();

        player.play("mp3", "song1.mp3");     // Directly supported
        player.play("vlc", "movie.vlc");     // Uses VlcAdapter internally
        player.play("mp4", "video.mp4");     // Uses Mp4Adapter internally
        player.play("avi", "file.avi");      // Unsupported format
    }
}

