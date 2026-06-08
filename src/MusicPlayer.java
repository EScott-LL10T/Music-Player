import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayer {
    private static int currentSong;
    private static final Scanner s = new Scanner(System.in);

    static void main(String[] args){
        musicPlayer();
    }

    public static void musicPlayer(){
        File folder = new File("src\\music");
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null){
            currentSong = 0;
            while(listOfFiles[currentSong] != null){
                File f = listOfFiles[currentSong];
                if(f.isFile()){
                    boolean exit = playSong(f);
                    if(currentSong == listOfFiles.length){
                        IO.println("End of song list.");
                        currentSong = listOfFiles.length - 1;
                    }

                    if(currentSong < 0){
                        IO.println("Start of track list.");
                        currentSong = 0;
                    }
                    if(exit){
                        IO.println("Bye.");
                        break;
                    }
                }else{
                    IO.println("not a file");
                    return;
                }
            }
        }else{
            IO.println("no files found.");
        }
    }

    public static boolean playSong(File file){
        final String MESSAGE = """
                S = Start\s
                P = Pause\s
                R = Reset\s
                B = Back\s
                N = Next\s
                Q = Quit\s
                Enter your choice:\s""";
        String response = "";
        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            while(!response.equals("Q") && !response.equals("N") && !response.equals("B")) {
                IO.println(MESSAGE);
                response = s.nextLine().toUpperCase();
                switch(response){
                    case "S" -> clip.start();
                    case "P" -> clip.stop();
                    case "R" -> clip.setMicrosecondPosition(0);
                    case "Q" -> clip.close();
                    case "B" -> currentSong--;
                    case "N" -> currentSong++;
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


