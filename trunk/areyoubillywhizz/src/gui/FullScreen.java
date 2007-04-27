package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

import parser.TimerCodeParser.TimerState;

import timer.Session;

public class FullScreen implements DisplayUpdateHandler {

    private String time = "00:00.00";
    
    private Color fontColour = Color.RED;

    private Frame frame;

    private Rectangle bounds;

    private BufferStrategy bufferStrategy;

    public FullScreen() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment
                .getDefaultScreenDevice();

        frame = new Frame();
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        graphicsDevice.setFullScreenWindow(frame);
        frame.createBufferStrategy(2); // 2 buffers
        bounds = frame.getBounds();
        bufferStrategy = frame.getBufferStrategy();
        render();
    }

    private void render() {

        Graphics g = null;
        try {
            g = bufferStrategy.getDrawGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, bounds.width, bounds.height);
            
            g.setColor(fontColour);
            g.setFont(new Font("DS-Digital", Font.BOLD, 330));
            g.drawString(time, 100, 500);
            
            bufferStrategy.show();
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
    }

    public void newTimeString(String time) {
        this.time = time;
        render();
    }

    public void newLeftState(boolean isPressed) {
    }

    public void newRightState(boolean isPressed) {
    }

    public void newSession(Session session) {
    }

    public void newState(TimerState state) {
        if (state == TimerState.STANDBY) {
            fontColour = Color.GREEN;
        } else {
            fontColour = Color.RED;
        }
    }
}