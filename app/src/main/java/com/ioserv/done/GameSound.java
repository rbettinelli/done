package com.ioserv.done;

import android.content.Context;
import android.media.MediaPlayer;
import java.util.HashSet;
import java.util.Set;

public class GameSound {

    private static Set<MediaPlayer> activePlayers = new HashSet<MediaPlayer>();

    public void playSound(Context context,Integer idx) {
        MediaPlayer player = MediaPlayer.create(context, idx);
        activePlayers.add(player);
        player.setOnCompletionListener(releaseOnFinishListener);
        player.start();
    }

    MediaPlayer.OnCompletionListener releaseOnFinishListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            activePlayers.remove(mp);
        }
    };

}
