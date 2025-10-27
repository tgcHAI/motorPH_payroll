package OOP;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Window;
import javax.swing.Timer;

public class SessionTimeoutManager {

    public static final boolean TEST_MODE = true; // Set to false in production
    
   //controls the time beforre Session timeout kicks in
   public static final long TIMEOUT_DURATION = TEST_MODE ? 5_000 : 10 * 60_000;

    private Timer idleTimer;
    private AWTEventListener activityListener;
    private Window associatedWindow;
    private Runnable onTimeoutAction;

    // Prevent multiple instances per window
    private static SessionTimeoutManager currentInstance;

    private SessionTimeoutManager(Window window, Runnable onTimeout) {
        this.associatedWindow = window;
        this.onTimeoutAction = onTimeout;
        setupTimer();
        setupActivityListener();
        setupWindowCleanup();
    }

    /*
     * Start session timeout for the given window.
     * @param window The window to monitor (e.g., JFrame, JDialog)
     * @param onTimeout Action to run when timeout occurs
     */
    public static void start(Window window, Runnable onTimeout) {
        if (currentInstance != null) {
            currentInstance.stop(); // Stop previous instance
        }
        currentInstance = new SessionTimeoutManager(window, onTimeout);
    }
    
    //Manually stop the timeout manager (optional, auto-stops on window close)
    public static void stop() {
        if (currentInstance != null) {
            currentInstance.cleanup();
            currentInstance = null;
        }
    }

    private void setupTimer() {
        idleTimer = new Timer((int) TIMEOUT_DURATION, e -> {
            onTimeoutAction.run();
            stop(); // Auto-stop after timeout
        });
        idleTimer.setRepeats(false);
        resetTimer();
    }

    private void setupActivityListener() {
        activityListener = event -> {
            if (event instanceof MouseEvent) {
                if (((MouseEvent) event).getID() == MouseEvent.MOUSE_CLICKED) {
                    resetTimer();
                }
            } else if (event instanceof KeyEvent) {
                int id = ((KeyEvent) event).getID();
                if (id == KeyEvent.KEY_TYPED || id == KeyEvent.KEY_PRESSED) {
                    resetTimer();
                }
            }
        };

        Toolkit.getDefaultToolkit().addAWTEventListener(activityListener,
            AWTEvent.MOUSE_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
    }

    private void setupWindowCleanup() {
        associatedWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
            }
        });
    }

    private void resetTimer() {
        if (idleTimer.isRunning()) {
            idleTimer.stop();
        }
        idleTimer.start();
    }

    private void cleanup() {
        if (idleTimer != null) {
            idleTimer.stop();
        }
        if (activityListener != null) {
            Toolkit.getDefaultToolkit().removeAWTEventListener(activityListener);
        }
    }
}