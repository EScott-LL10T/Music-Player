import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayer {
    private static int currentSong;

    static void main(String[] args){
        musicPlayer();
    }

    public static void musicPlayer(){
        File folder = new File("src\\music");
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null){
            currentSong = 0;
            for(int i = 0; i < listOfFiles.length; i++){
                File f = listOfFiles[i];
                if(f.isFile()){
                    boolean exit = playSong(f);
                    if(exit){
                        IO.println("Bye");
                        break;
                    }
                }
            }
        }else{
            IO.println("no files found");
        }
    }

    public static boolean playSong(File file){
        final String MESSAGE = "P = Play \nS = stop \nR = reset \nN = next \nP = prev \nQ = quit \nEnter your choice: ";
        String response = "";
        try(Scanner s = new Scanner(System.in);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            while(!response.equals("Q")) {
                IO.println(MESSAGE);
                response = s.nextLine().toUpperCase();
                switch(response){
                    case "P" -> clip.start();
                    case "S" -> clip.stop();
                    case "R" -> clip.setMicrosecondPosition(0);
                    case "Q" -> {
                        clip.close();
                    }
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
        return response.equals("Q");
    }
}


