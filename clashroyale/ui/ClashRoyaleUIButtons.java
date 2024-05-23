package clashroyale.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ClashRoyaleUIButtons extends JButton {
    private int Xcoord;
    private int Ycoord;

    public ClashRoyaleUIButtons(int X, int Y) {
        this.Xcoord = X;
        this.Ycoord = Y;

    }

    public int[] getCoordinates() {
        int[] coords = {Xcoord, Ycoord};
        return coords;
    }

    public void setCard(String s) {
        if(s.equals("Archer - red")){
            this.setIcon(new ImageIcon("./GUIImages/ArcherRed.png"));
        } else if(s.equals("Archer - blue")){
            this.setIcon(new ImageIcon("./GUIImages/ArcherBlue.png"));
        } else if(s.equals("Dragon - blue")) {
            this.setIcon(new ImageIcon("./GUIImages/DragonBlue.png"));
        } else if(s.equals("Dragon - red")){
            this.setIcon(new ImageIcon("./GUIImages/DragonRed.png"));
        } else if(s.equals("Goth - red")) {
            this.setIcon(new ImageIcon("./GUIImages/GothRed.png"));
        }else if(s.equals("Goth - blue")){
            this.setIcon(new ImageIcon("./GUIImages/GothBlue.png"));
        } else if(s.equals("Knight - red")) {
            this.setIcon(new ImageIcon("./GUIImages/KingRed.png"));
        } else if(s.equals("Knight - blue")) {
            this.setIcon(new ImageIcon("./GUIImages/KingBlue.png"));
        } else if(s.equals("Musketeer - red")){
            this.setIcon(new ImageIcon("./GUIImages/MusketeerRed.png"));
        } else if(s.equals("Musketeer - blue")){
            this.setIcon(new ImageIcon("./GUIImages/MusketeerBlue.png"));
        } else if(s.equals("Valkyrie - red")) {
            this.setIcon(new ImageIcon("./GUIImages/ValkyrieRed.png"));
        } else if(s.equals("Valkyrie - blue")){
            this.setIcon(new ImageIcon("./GUIImages/ValkyrieBlue.png"));
        } else if(s.equals("Witch - red")){
            this.setIcon(new ImageIcon("./GUIImages/WitchRed.png"));
        } else if(s.equals("Witch - blue")){
            this.setIcon(new ImageIcon("./GUIImages/WitchBlue.png"));
        } else if(s.equals("Witchbaby - red")){
            this.setIcon(new ImageIcon("./GUIImages/WitchBabyRed.png"));
        } else if(s.equals("Witchbaby - blue")){
            this.setIcon(new ImageIcon("./GUIImages/WitchBabyBlue.png"));
        }else if(s.equals("Fireball - red")) {
            this.setIcon(new ImageIcon("./GUIImages/Fireball.jpeg"));
        }else if(s.equals("Fireball - blue")){
            this.setIcon(new ImageIcon("./GUIImages/Fireball.jpeg"));
        }else if(s.equals("Bow - red")){
            this.setIcon(new ImageIcon("./GUIImages/Bow.png"));
        }else if(s.equals("Bow - blue")){
            this.setIcon(new ImageIcon("./GUIImages/Bow.png"));
        }
    }

    public void setCard() {
        this.setIcon(null);
    }
}

