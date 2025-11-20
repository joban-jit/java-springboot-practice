package com.practice.designPatterns.adapter;

/*
You have existing classes or third-party libraries with interfaces incompatible with your existing code.
You want to reuse these components without modifying their source code.
It promotes code reusability and loose coupling by isolating the adaptation logic from the core business logic.
 */

// Target interface
interface MediaPlayer {
    void play(String fileType, String fileName);
}

// Adaptee with different interface
 class VlcPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}

 class Mp4Player {
    public void playMp4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
}



class VlcAdapter  implements MediaPlayer {

    private VlcPlayer vlcPlayer;

    public VlcAdapter() {
        this.vlcPlayer = new VlcPlayer();
    }


    @Override
    public void play(String fileType, String fileName) {
        if ("vlc".equalsIgnoreCase(fileType)) {
            vlcPlayer.playVlc(fileName);
        }
    }

}

class Mp4Adapter implements MediaPlayer {
    private Mp4Player mp4Player;

    public Mp4Adapter() {
        mp4Player = new Mp4Player();
    }

    @Override
    public void play(String fileType, String fileName) {
        if ("mp4".equalsIgnoreCase(fileType)) {
            mp4Player.playMp4(fileName);
        }
    }
}

public class AudioPlayer implements MediaPlayer{
    private MediaPlayer mediaAdapter;

    @Override
    public void play(String fileType, String fileName) {
        switch (fileType){
            case "mp3" -> System.out.println("Playing MP3 file: "+fileName);
            case "mp4" -> {
                mediaAdapter = new Mp4Adapter();
                mediaAdapter.play(fileType, fileName);
            }
            case "vlc"->{
                mediaAdapter = new VlcAdapter();
                mediaAdapter.play(fileType, fileName);
            }
            default -> System.out.println("Invalid media. " + fileType + " format not supported");
        }

    }
}
