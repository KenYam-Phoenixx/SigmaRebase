package com.mentalfrostbyte.jello.gui.impl.jello.ingame.clickgui.musicplayer.buttons;

import com.mentalfrostbyte.Client;
import com.mentalfrostbyte.jello.gui.base.CustomGuiScreen;
import com.mentalfrostbyte.jello.gui.unmapped.AnimatedIconPanelWrap;
import com.mentalfrostbyte.jello.gui.unmapped.YoutubeVideoThumbnail;
import com.mentalfrostbyte.jello.gui.impl.jello.buttons.ScrollableContentPanel;
import com.mentalfrostbyte.jello.gui.impl.jello.buttons.TextField;
import com.mentalfrostbyte.jello.managers.MusicManager;
import com.mentalfrostbyte.jello.util.client.network.youtube.YoutubeJPGThumbnail;
import com.mentalfrostbyte.jello.util.client.network.youtube.YoutubeVideoData;
import com.mentalfrostbyte.jello.util.client.ColorHelper;
import com.mentalfrostbyte.jello.util.client.network.youtube.ThumbnailUtil;

import java.util.ArrayList;

public class SearchBox extends AnimatedIconPanelWrap {
    public ScrollableContentPanel field20840;
    public TextField searchBox;
    private ArrayList<YoutubeVideoData> field20842;
    private final MusicManager field20843 = Client.getInstance().musicManager;

    public SearchBox(CustomGuiScreen var1, String var2, int var3, int var4, int var5, int var6, String var7) {
        super(var1, var2, var3, var4, var5, var6, ColorHelper.field27961, var7, false);
        this.addToList(this.field20840 = new ScrollableContentPanel(this, "albumView", 0, 0, var5, var6, ColorHelper.field27961, "View"));
        this.addToList(this.searchBox = new TextField(this, "searchInput", 30, 14, var5 - 60, 70, TextField.field20742, "", "Search..."));
        this.searchBox.method13292(true);
    }

    @Override
    public void draw(float partialTicks) {
        super.draw(partialTicks);
    }

    @Override
    public void keyPressed(int keyCode) {
        if (keyCode == 257 && this.searchBox.method13297()) {
            this.searchBox.method13145(false);
            new Thread(
                    () -> {
                        this.field20842 = new ArrayList<>();
                        YoutubeJPGThumbnail[] var3 = ThumbnailUtil.search(this.searchBox.getTypedText());

                        for (YoutubeJPGThumbnail var7 : var3) {
                            this.field20842.add(new YoutubeVideoData(var7.videoID, var7.title, var7.fullUrl));
                        }

                        this.runThisOnDimensionUpdate(
                                () -> {
                                    this.method13236(this.field20840);
                                    this.addToList(
                                            this.field20840 = new ScrollableContentPanel(this, "albumView", 0, 0, this.widthA, this.heightA, ColorHelper.field27961, "View")
                                    );
                                    if (this.field20842 != null) {
                                        for (int var3x = 0; var3x < this.field20842.size(); var3x++) {
                                            YoutubeVideoData var4 = this.field20842.get(var3x);
                                            YoutubeVideoThumbnail var7x;
                                            this.field20840
                                                    .addToList(
                                                            var7x = new YoutubeVideoThumbnail(
                                                                    this.field20840,
                                                                    10 + var3x % 3 * 183 - (var3x % 3 <= 0 ? 0 : 10) - (var3x % 3 <= 1 ? 0 : 10),
                                                                    80 + 10 + (var3x - var3x % 3) / 3 * 210,
                                                                    183,
                                                                    220,
                                                                    var4
                                                            )
                                                    );
                                            var7x.doThis((var2, var3xx) -> this.field20843.playSong(null, var4));
                                        }
                                    }
                                }
                        );
                    }
            )
                    .start();
        }

        super.keyPressed(keyCode);
    }
}
