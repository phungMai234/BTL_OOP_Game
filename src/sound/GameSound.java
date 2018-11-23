package sound;


import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

/* note: muon doi file nhac, chọn trình chuyen doi online sang duoi wav thì mơi chay dc, k doi tay*/

public class GameSound {
    public static GameSound instance;

    public static final String PLAYGAME = "nhacnen (online-audio-converter.com).wav";
    public static final String BOMB = "newbomb.wav";
//    public static final String BOMBER_DIE = "bomber_die.wav";
//    public static final String MONSTER_DIE = "monster_die.wav";
    public static final String BOMB_BANG = "bomb_bang.wav";
//    public static final String ITEM = "item.wav";
//    public static final String WIN = "win.wav";
    public static final String LOSE = "gameover (online-audio-converter.com).wav";

    private HashMap<String, AudioClip> audioMap;

    public GameSound() {
        audioMap = new HashMap<>();		//tao hashmap audioMap
        loadAllAudio();
    }

    public static GameSound getIstance() {
        if (instance == null) {
            instance = new GameSound();
        }

        return instance;
    }

    public void loadAllAudio() {

        setAudio(PLAYGAME);
        setAudio(BOMB);
//        setAudio(MONSTER_DIE);
//        setAudio(BOMBER_DIE);
        setAudio(BOMB_BANG);
//        setAudio(ITEM);
//        setAudio(WIN);
        setAudio(LOSE);
    }

    public void stop() {

        getAudio(PLAYGAME).stop();
        getAudio(BOMB).stop();
        getAudio(BOMB_BANG).stop();
//        getAudio(WIN).stop();
        getAudio(LOSE).stop();
    }

    public void setAudio(String name) {
        audioMap.put(name, Applet.newAudioClip(GameSound.class.getResource(name)));
    }

    public AudioClip getAudio(String name) {
        return audioMap.get(name);
    }
}

