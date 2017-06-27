/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import Database.DBEventModifier;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author David
 */
public class Utility {

    private static Object lock;
    private static boolean done = false;
    
    /**
     * Tells a random object to wait while in a loop. The loop stops, and won't
     * cause any unnecessary cpu use.
     */
    public static void lockFXThread() {
        lock = new Object();
        synchronized (lock) {
            while (!done) {
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DBEventModifier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        done = false;
    }

    /**
     * Wakes the lock. The while loop in the method 'lockFXThread' will proceed
     * and break free.
     */
    public static void unlockFXThread() {
        synchronized (lock) {
            done = true;
            lock.notifyAll();
        }
    }
}
