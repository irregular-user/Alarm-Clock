import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable{

    private final LocalTime alarmTime;
    private final String songFile;
    private final Scanner scanner;

    AlarmClock(LocalTime alarmTime, String songFile, Scanner scanner){
        this.alarmTime = alarmTime;
        this.songFile = songFile;
        this.scanner = scanner;
    }
    @Override
    public void run() {

        while(LocalTime.now().isBefore(alarmTime)) {
            try {
                Thread.sleep(1000);
                LocalTime now = LocalTime.now();

                System.out.printf("\r%02d:%02d:%02d",
                                now.getHour(),
                                now.getMinute(),
                                now.getSecond());
            } catch (InterruptedException e) {
                System.out.println("This thread failed unexpectedly!");
            }
        }

        System.out.println("\n*ALARM*");
        playAudio(this.songFile);
    }

    private void playAudio(String songFile){

        File audioFile = new File(songFile);

        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            System.out.println("Press *Enter* to stop the alarm.");
            scanner.nextLine();
            clip.stop();



            scanner.close();


        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file format is not supported");
        } catch (IOException e) {
            System.out.println("Error reading audio file");
        } catch (LineUnavailableException e) {
            System.out.println("Audio is unavailable");
        }

    }
}
