import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    static void main(String[] args){
        musicPlayer();
    }

    public static void musicPlayer(){
        String filePath = "Covenant Dance.wav";
        File file = new File(filePath);
        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){

        }catch(IOException e){
            IO.println("something went wrong.");
        } catch (UnsupportedAudioFileException e) {
            IO.println("audio file is not supported. Please use .wav, .au or .aiff");
        }
    }
}
