import java.util.Scanner;

interface MediaPlayer {
    void play();
    void pause();
    void stop();
}

class AudioPlayer implements MediaPlayer {
    public void play() {
        System.out.println("Playing audio...");
    }

    public void pause() {
        System.out.println("Audio paused.");
    }

    public void stop() {
        System.out.println("Audio stopped.");
    }
}

class VideoPlayer implements MediaPlayer {
    public void play() {
        System.out.println("Playing video...");
    }

    public void pause() {
        System.out.println("Video paused.");
    }

    public void stop() {
        System.out.println("Video stopped.");
    }
}

public class Media_Player {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Media Type: 1. Audio  2. Video");
        int type = scanner.nextInt();

        MediaPlayer player = null;

        if (type == 1) {
            player = new AudioPlayer();
        } else if (type == 2) {
            player = new VideoPlayer();
        } else {
            System.out.println("Invalid type.");
            scanner.close();
            return;
        }

        System.out.println("Choose Action: 1. Play  2. Pause  3. Stop");
        int action = scanner.nextInt();

        if (action == 1) {
            player.play();
        } else if (action == 2) {
            player.pause();
        } else if (action == 3) {
            player.stop();
        } else {
            System.out.println("Invalid action.");
        }

        scanner.close();
    }
}
