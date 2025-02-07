package com.mentalfrostbyte.jello.gui.impl.jello.ingame.clickgui.musicplayer.buttons;

import com.mentalfrostbyte.jello.gui.impl.jello.ingame.clickgui.musicplayer.MusicPlayer;
import com.mentalfrostbyte.jello.gui.unmapped.Button;
import com.mentalfrostbyte.jello.gui.unmapped.YoutubeVideoThumbnail;
import com.mentalfrostbyte.jello.gui.impl.jello.buttons.ScrollableContentPanel;
import com.mentalfrostbyte.jello.managers.MusicVideoManager;
import com.mentalfrostbyte.jello.util.client.network.youtube.YoutubeVideoData;
import com.mentalfrostbyte.jello.util.client.ColorHelper;
import com.mentalfrostbyte.jello.util.client.render.ResourceRegistry;

public class Songs implements Runnable {
    public final MusicVideoManager thumbnail;
    public final ColorHelper colorHelper;
    public final MusicPlayer musicPlayer;
    public final MusicPlayer musicPlayer2;

    public Songs(MusicPlayer var1, MusicVideoManager var2, ColorHelper var3, MusicPlayer var4) {
        this.musicPlayer2 = var1;
        this.thumbnail = var2;
        this.colorHelper = var3;
        this.musicPlayer = var4;
    }

    @Override
    public void run() {
        if (!MusicPlayer.getTabs(this.musicPlayer2).isntQueue(this.thumbnail.videoId)) {
            Button var3;
            MusicPlayer.getTabs(this.musicPlayer2)
                    .addToList(
                            var3 = new Button(
                                    MusicPlayer.getTabs(this.musicPlayer2),
                                    this.thumbnail.videoId,
                                    0,
                                    MusicPlayer.getTabs(this.musicPlayer2).getButton().getChildren().size() * MusicPlayer.getHeight(this.musicPlayer2),
                                    MusicPlayer.getWidth(this.musicPlayer2),
                                    MusicPlayer.getHeight(this.musicPlayer2),
                                    this.colorHelper,
                                    this.thumbnail.name,
                                    ResourceRegistry.JelloLightFont14
                            )
                    );
            ScrollableContentPanel queue;
            this.musicPlayer
                    .addToList(
                            queue = new ScrollableContentPanel(
                                    this.musicPlayer,
                                    this.thumbnail.videoId,
                                    MusicPlayer.getWidth(this.musicPlayer2),
                                    0,
                                    this.musicPlayer.getWidthA() - MusicPlayer.getWidth(this.musicPlayer2),
                                    this.musicPlayer.getHeightA() - MusicPlayer.method13209(this.musicPlayer2),
                                    ColorHelper.field27961,
                                    this.thumbnail.name
                            )
                    );
            queue.method13514(true);
            queue.setEnabled(false);
            queue.setListening(false);
            if (this.thumbnail.videoList != null) {
                for (int i = 0; i < this.thumbnail.videoList.size(); i++) {
                    YoutubeVideoData song = this.thumbnail.videoList.get(i);
                    YoutubeVideoThumbnail thumbnail;
                    int x = 65;
                    int y = 10;
                    if (!queue.isntQueue(this.thumbnail.videoId)) {
                        queue.addToList(
                                thumbnail = new YoutubeVideoThumbnail(
                                        queue,
                                        y + i % 3 * 183 - (i % 3 <= 0 ? 0 : y) - (i % 3 <= 1 ? 0 : y),
                                        x + y + (i - i % 3) / 3 * 210,
                                        183,
                                        220,
                                        song
                                )
                        );
                        thumbnail.doThis((parent, idk) -> {
                            if (this.musicPlayer.parent.hasJelloMusicRequirements())
                                MusicPlayer.playSong(this.musicPlayer2, this.thumbnail, song);
                        });
                    }
                }
            }

            var3.doThis((var2, var3x) -> MusicPlayer.method13210(this.musicPlayer2, queue));
        }
    }
}
