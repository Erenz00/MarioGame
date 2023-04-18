/*
 * Copyright (c) 2008 Golden T Studios.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.golden.gamedev.funbox;

// JFC
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import com.golden.gamedev.engine.graphics.WindowExitListener;
import com.golden.gamedev.util.ImageUtil;
import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JComboBox;

/**
 * <code>GameSettings</code> is a dialog UI to show the user the basic game
 * options for the player to choose of.
 * <p>
 * 
 * The options includes : <br>
 * Option to play between fullscreen or windowed mode, option to play using
 * bufferstrategy or not, and option turn on or turn off the sound.
 * <p>
 * 
 * To give the user more options or remove some options, subclass this class and
 * override the {@link #initSettings() initSettings()} method.
 * <p>
 * 
 * Example of how-to-use <code>GameSettings</code> class :
 * 
 * <pre>
 * GameSettings settings = new GameSettings() {
 * 	
 * 	public void start() {
 * 		// here goes the usual game initialization
 * 		GameLoader game = new GameLoader();
 * 		game.setup(new RoboticsWarGame() {
 * 			
 * 			protected void initEngine() {
 * 				super.initEngine();
 * 				// set active sound base on user setting
 * 				bsSound.setActive(sound.isSelected());
 * 				bsMusic.setActive(sound.isSelected());
 * 			}
 * 		}, new Dimension(640, 480), fullscreen.isSelected(), bufferstrategy
 * 		        .isSelected());
 * 		game.start();
 * 	}
 * 	
 * 	// example removing bufferstrategy option from the list
 * 	// then adding new option to use OpenGL or not
 * 	JCheckBox opengl;
 * 	
 * 	protected JPanel initSettings() {
 * 		JPanel pane = super.initSettings();
 * 		// remove bufferstrategy option
 * 		pane.remove(bufferstrategy);
 * 		// add opengl option
 * 		opengl = new JCheckBox(&quot;OpenGL&quot;, true);
 * 		pane.add(opengl, 0);
 * 	}
 * };
 * </pre>
 * 
 * @see #initSettings()
 */
public abstract class GameSettings extends JDialog implements ActionListener,
        Runnable {

    /** *************************** CHECK BOX UI ******************************** */
    /**
     * Check box UI for SkipSetting option.
     */
//    private Dimension dimension;
    protected JCheckBox Skip;
    public javax.swing.JTextField Game_Width;
    public javax.swing.JTextField Game_Height;
//    javax.swing.j
    /**
     * Check box UI for fullscreen option.
     */
    protected JCheckBox fullscreen;
    /**
     * Check box UI for bufferstrategy option.
     */
    protected JCheckBox bufferstrategy;
    /**
     * Check box UI for sound option.
     */
    protected JCheckBox sound;
    /**
     * The OK button, clicking this button will launch the game.
     * 
     * @see #actionPerformed(ActionEvent)
     */
    protected JButton btnOK;
    /**
     * The Cancel button, clicking this button will abort the game.
     * 
     * @see #actionPerformed(ActionEvent)
     */
    protected JButton btnCancel;
    /**
     * The splash image URL, null if the game has no splash image.
     */
    protected URL splashImage;
//    private final Dimension dimension;

    /** ************************************************************************* */
    /** ************************** CONSTRUCTOR ********************************** */
    /** ************************************************************************* */
    /**
     * Builds up the game settings/options dialog for the user with specified
     * splash image URL or null .
     * <p>
     * 
     * For example :
     * 
     * <pre>
     * new GameSettings(YourGame.class.getResource(&quot;splash.jpg&quot;)) {
     * 	
     * 	public void start() {
     * 		// create the actual game in here
     * 	}
     * };
     * </pre>
     * 
     * @param splashImage The {@link URL} of the splash image or null.
     * @see com.golden.gamedev.GameLoader
     */
    public GameSettings(URL splashImage) throws FileNotFoundException, IOException {
        super((Frame) null, "Settings", true);

        // init dialog
        this.setResizable(false);
        this.addWindowListener(WindowExitListener.getInstance());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // init GUI
        this.splashImage = splashImage;
        JPanel contentPane = this.initGUI();
        this.setContentPane(contentPane);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Builds up the game settings/options dialog for the player without splash
     * image.
     */
    public GameSettings() throws FileNotFoundException, IOException {
        this(null);
    }

    /**
     * Skips the option dialog, and start the game with specified mode and
     * bufferstrategy.
     * <p>
     * 
     * Used for direct play without specifying the game option everytime running
     * the game while in developing stage.
     * @param fullscreen If fullscreen is used.
     * @param bufferStrategy If a buffer strategy is used.
     */
    public GameSettings(boolean fullscreen, boolean bufferStrategy, boolean sound) throws FileNotFoundException, IOException {
        this.initGUI();

        this.fullscreen.setSelected(fullscreen);
        this.bufferstrategy.setSelected(bufferStrategy);
        this.sound.setSelected(sound);
//        this.dimension = dimension;

        this.dispose();
        this.start();
    }

    /**
     * Skips the option dialog, and directly init the game with specified mode
     * and using bufferstrategy by default.
     * <p>
     * 
     * Used in game development, so no need to always specify the game option
     * everytime running the game while in developing stage.
     * @param fullscreen If fullscreen is used.
     */
    public GameSettings(boolean fullscreen) throws FileNotFoundException, IOException {
//        this(fullscreen, true, false);
    }

    /** ************************************************************************* */
    /** ************************** GUI INITIALIZATION *************************** */
    /** ************************************************************************* */
    /**
     * Initializes the content of the settings, by default consists of splash
     * image (if any), {@linkplain #initSettings() settings panel}, and launch
     * panel.
     * <p>
     * 
     * Example of modifying some UI components : <br>
     * The first component is splash image (JLabel), settings panel (JPanel),
     * and launch panel (JPanel), using BorderLayout
     * 
     * <pre>
     * protected JPanel initGUI() {
     * 	JPanel pane = super.initGUI();
     * 	// modify launch panel, add Credits button
     * 	JPanel launchPane = (JPanel) pane.getComponent(2);
     * 	launchPane.add(new JButton(&quot;Credits&quot;), 0);
     * 	// remove splash image
     * 	pane.remove(0);
     * 	// insert splash image
     * 	pane.add(new JButton(&quot;Splash Image&quot;), BorderLayout.NORTH);
     * }
     * </pre>
     * 
     * @return The <code>GameSettings</code> content pane.
     * @see #initSettings()
     */
    protected JPanel initGUI() throws FileNotFoundException, IOException {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        // splash image
        JLabel splashLabel = null;
        if (this.splashImage != null) {
            try {
                splashLabel = new JLabel(new ImageIcon(ImageUtil.getImage(this.splashImage)));
            } catch (Exception e) {
                splashLabel = null;
            }
        }

        // option panel
        JPanel optionPane = this.initSettings();

        // submit pane
        JPanel submitPane = new JPanel();
        submitPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        submitPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));
        // start button
        this.btnOK = new JButton("OK");
        this.btnOK.addActionListener(this);
        this.getRootPane().setDefaultButton(this.btnOK);

        // cancel button
        this.btnCancel = new JButton("Cancel");
        this.btnCancel.addActionListener(this);
        submitPane.add(this.btnOK);
        submitPane.add(this.btnCancel);

        if (splashLabel != null) {
            contentPane.add(splashLabel, BorderLayout.NORTH);
        }
        if (optionPane != null) {
            contentPane.add(optionPane, BorderLayout.CENTER);
        }
        contentPane.add(submitPane, BorderLayout.SOUTH);

        return contentPane;
    }

    /**
     * Initializes and return the settings GUI, override this method to insert
     * new option or remove some options.
     * <p>
     * 
     * For example :
     * 
     * <pre>
     * JCheckBox opengl;
     * 
     * protected JPanel initSettings() {
     * 	JPanel pane = super.initSettings();
     * 	// add opengl option
     * 	opengl = new JCheckBox(&quot;OpenGL&quot;);
     * 	// remove bufferstrategy option
     * 	pane.remove(bufferstrategy);
     * 	// set windowed mode by default
     * 	fullscreen.setSelected(false);
     * }
     * </pre>
     * 
     * @return The settings panel where all the settings are layed out.
     */
    protected JPanel initSettings() throws FileNotFoundException, IOException {

        boolean fullscreenData = true;
        boolean bufferStrategyData = true;
        boolean soundData = true;
        boolean SkipData = false;


        Game_Width = new javax.swing.JTextField();
        Game_Height = new javax.swing.JTextField();

        Game_Width.setText("640");
        Game_Height.setText("480");

        File SETTINGS = new File("Setting.txt");
        if (SETTINGS.exists()) {
            FileInputStream fin = new FileInputStream(SETTINGS);
            DataInputStream in = new DataInputStream(fin);
            try {
                fullscreenData = in.readBoolean();

                bufferStrategyData = in.readBoolean();
                soundData = in.readBoolean();
                SkipData = in.readBoolean();
                Game_Width.setText(in.readInt() + "");
                Game_Height.setText(in.readInt() + "");

                if (Integer.parseInt(Game_Width.getText()) == 0 || "".equals(Game_Width.getText())){
                    Game_Width.setText("640");
                    SETTINGS.delete();
                }
                
                if (Integer.parseInt(Game_Height.getText()) == 0 || "".equals(Game_Height.getText())){
                    Game_Height.setText("480");
                    SETTINGS.delete();
                }
                in.close();
//                SETTINGS.delete();

            } catch (java.io.EOFException e ) {
                SETTINGS.delete();
                fullscreenData = false;
                bufferStrategyData = false;
                soundData = false;
                SkipData = false;
                Game_Width.setText("640");
                Game_Height.setText("480");

            }catch (java.lang.NumberFormatException e){
                SETTINGS.delete();
                Game_Width.setText("640");
                Game_Height.setText("480");
            }
        }

        JPanel optionPane = new JPanel();
        optionPane.setLayout(new GridLayout(0, 2));
        Border border = BorderFactory.createEmptyBorder(6, 4, 3, 4);
        optionPane.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createTitledBorder("Game Settings")));

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // full screen
        boolean supportFSEM = true;
        try {
            supportFSEM = device.isFullScreenSupported();
        } catch (Throwable e) {
            e.printStackTrace();
            supportFSEM = false;
        }
        Skip = new JCheckBox("Skip This Next Time.", SkipData);



        Game_Width.setToolTipText("Game Width");
        Game_Height.setToolTipText("Game Height");

//        this.Game_Width.setText(dimension.getWidth() + "");
//        this.Game_Height.setText(dimension.getHeight() + "");
//        Resoultion.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"640 x 480", "800 x 600", "1024 x 768" , "1280 x 960"}));
//        FPS.

//        Resoultion.setSelectedIndex(resolution);

        this.fullscreen = new JCheckBox("Fullscreen", fullscreenData);
        this.fullscreen.setEnabled(supportFSEM);
        this.fullscreen.setToolTipText((supportFSEM) ? "fullscreen/windowed mode"
                : "fullscreen mode not supported");

        // buffer strategy
        this.bufferstrategy = new JCheckBox("Bufferstrategy", bufferStrategyData);
        this.bufferstrategy.setToolTipText("turn this off if the game experiencing graphics problem");

        // sound
        this.sound = new JCheckBox("Sound", soundData);
        this.sound.setToolTipText("turn on/off sound and music");

        optionPane.add(this.fullscreen);
        optionPane.add(this.bufferstrategy);
        optionPane.add(this.sound);
        optionPane.add(this.Skip);
        optionPane.add(this.Game_Width);
        optionPane.add(this.Game_Height);

        return optionPane;
    }

    /**
     * Notified when the user press 'OK' or 'Cancel' button.
     * <p>
     * 
     * Pressing 'OK' will launch the game, while pressing 'Cancel' will
     * immediatelly close the app.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnOK) {
            FileOutputStream fout = null;
            try {
                File SETTINGS = new File("Setting.txt");
                fout = new FileOutputStream(SETTINGS);
                DataOutputStream out = new DataOutputStream(fout);
                out.writeBoolean(fullscreen.isSelected());
                out.writeBoolean(bufferstrategy.isSelected());
                out.writeBoolean(sound.isSelected());
                out.writeBoolean(Skip.isSelected());
                out.writeInt(Integer.parseInt(Game_Width.getText()));
                out.writeInt(Integer.parseInt(Game_Height.getText()));
                out.close();


                // dispose this frame
                this.dispose();
                new Thread(this).start();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fout.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == this.btnCancel) {
            System.exit(0);
        }
    }

    /**
     * Thread implementation when the user pressing 'OK' button, directly called
     * {@link #start()} method.
     */
    @Override
    public void run() {
        this.start();
    }

    /** ************************************************************************* */
    /** ************************** START THE GAME ******************************* */
    /** ************************************************************************* */
    /**
     * Starts the game with all the defined settings, the game is actually
     * created in this method.
     * <p>
     * 
     * This method is called when the user is pressing the 'OK' button.
     */
    public abstract void start();
}
