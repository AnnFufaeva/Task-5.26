package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class Main {

    public static void main(String[] args) throws Exception {

        Locale.setDefault(Locale.ROOT);
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        EventQueue.invokeLater(() -> {
            try {
                JFrame frameMain = new TreeFrame();
                frameMain.setVisible(true);
                frameMain.setSize(600, 500);
                frameMain.setLocationRelativeTo(null);
            } catch (Exception ex) {
                TreeFrame.showErrorMessageBox(null, "Error", ex);
            }
        });
    }
}
