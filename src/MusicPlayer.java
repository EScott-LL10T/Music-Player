import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayer {

    static void main(String[] args){
        musicPlayer();
    }

    public static void musicPlayer(){
        final String filePath = "src\\Covenant Dance.wav";
        File file = new File(filePath);
        final String MESSAGE = "P = Play \nS = stop \nR = reset \nQ = quit \nEnter your choice: ";
        try(Scanner s = new Scanner(System.in);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String response = "";
            while(!response.equals("Q")) {
                IO.println(MESSAGE);
                response = s.nextLine().toUpperCase();
                switch(response){
                    case "P" -> clip.start();
                    case "S" -> clip.stop();
                    case "R" -> clip.setMicrosecondPosition(0);
                    case "Q" -> clip.close();
                    default -> IO.println("Invalid choice.");
                }
            }

        }
        catch(FileNotFoundException e){
            IO.println("Couldn't locate file.");
        }
        catch(IOException e){
            IO.println("something went wrong.");
        }
        catch (UnsupportedAudioFileException e) {
            IO.println("audio file is not supported. Please use .wav, .au or .aiff");
        }
        catch (LineUnavailableException e) {
            IO.println("unable to access audio resource.");
        }
        finally{
            IO.println("bye");
        }
    }



}
