package clashroyale;

import clashroyale.cli.ClashRoyaleConsole;
import clashroyale.ui.ClashRoyaleUI;

public class Main {

    public static void main(String[] args) {
      if (args.length == 0) {
            // No command line arguments provided, create GUI
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
        } else if (args.length == 1 && args[0].equals("-console")) {
            // Single command line argument "-console" provided, use console UI
            ClashRoyaleConsole console = new ClashRoyaleConsole();
            console.play();
        }
       ClashRoyaleConsole console = new ClashRoyaleConsole();
        console.play();

    }
    private static void createAndShowGUI() {
        // Create and set up the main window
        ClashRoyaleUI clashRoyaleUI = new ClashRoyaleUI();
        clashRoyaleUI.setVisible(true);
    }

}
