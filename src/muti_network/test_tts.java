package muti_network;

  import com.sun.speech.freetts.Voice;
  import com.sun.speech.freetts.VoiceManager;

 public class test_tts {
    public static void main (String[] args) {
    	VoiceManager voiceManager = VoiceManager.getInstance();
    	Voice[] voices = voiceManager.getVoices();
    	for (int i = 0; i < voices.length; i++){
    	    System.out.print( "voice name is: " + voices[i].getName() +"\n");
    	}
    }
 }
//스피커가없으므로 2020_05_08 테스트보류