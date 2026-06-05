import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer {

    static void main(String[] args){
        musicPlayer();
    }

    public static void musicPlayer(){
        String filePath = "src\\Covenant Dance.wav";
        File file = new File(filePath);
        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            IO.println("no problems detected.");
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
    }
}
