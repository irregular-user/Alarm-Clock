import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Java Alarm Clock

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;
        String songFile = "Gotta_Love_Me.wav";

        while(alarmTime == null){
            try{
                System.out.print("Enter an alarm time (HH:MM:SS): ");
                String inputTime = scanner.nextLine();


                alarmTime = LocalTime.parse(inputTime, formatter);
                System.out.println("Alarm set for " + alarmTime);}
            catch (DateTimeParseException e) {
                System.out.println("Please enter a valid formatted time!");
            }
        }

        AlarmClock alarmClock = new AlarmClock(alarmTime, songFile, scanner);
        Thread alarmThread = new Thread(alarmClock);

        alarmThread.start();



    }
}
