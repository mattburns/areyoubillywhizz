package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

import parser.TimerCodeParser.TimerState;
import timer.Session;

public class FullScreen extends Frame implements DisplayUpdateHandler {

    private static final long serialVersionUID = -1869252707388924320L;
    private static final double FONT_TO_SCREEN_RATIO = 0.95;
    private String time = "00:00.00";
    private Color fontColour = Color.RED;
    private Font font;
    private int fontSize;
    private int fontXPos;
    private int fontYPos;
    private Rectangle bounds;
    private BufferStrategy bufferStrategy;

    public FullScreen() {
        
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment
                .getDefaultScreenDevice();

        setUndecorated(true);
        setIgnoreRepaint(true);
        graphicsDevice.setFullScreenWindow(this);
        createBufferStrategy(2); // 2 buffers
        bounds = getBounds();
        bufferStrategy = getBufferStrategy();
        calculateFontSize();
        render();
    }
    
    private void calculateFontSize() {
        int closestSizeInBounds = 0;
        int closestSizeOutOfBounds = 10000;
        double maxWidth = getBounds().width * FONT_TO_SCREEN_RATIO;
        double maxHeight = getBounds().height * FONT_TO_SCREEN_RATIO;
        Rectangle2D fontBounds = null;
        
        while ((closestSizeOutOfBounds - closestSizeInBounds) > 1) {
            fontSize = closestSizeInBounds + ((closestSizeOutOfBounds - closestSizeInBounds) / 2);
            font = new Font("DS-Digital", Font.BOLD, fontSize);
            Graphics2D g2d = (Graphics2D)bufferStrategy.getDrawGraphics();
            fontBounds = font.getStringBounds(time, g2d.getFontRenderContext());
            if (fontBounds.getWidth() > maxWidth || fontBounds.getHeight() > maxHeight) {
                closestSizeOutOfBounds = fontSize;
            } else {
                closestSizeInBounds = fontSize;
            }
        }
        fontXPos = (int)((getBounds().width - fontBounds.getWidth()) / 2);
        fontYPos = (int)((getBounds().height / 2) + (fontBounds.getHeight()*0.6 / 2)); // actuall font height seems to be about 60% of what is reported
    }

    private void render() {
        Graphics2D g = null;
        try {
            g = (Graphics2D)bufferStrategy.getDrawGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, bounds.width, bounds.height);
            
            g.setColor(fontColour);
            g.setFont(font);
            g.drawString(time, fontXPos, fontYPos);
            
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