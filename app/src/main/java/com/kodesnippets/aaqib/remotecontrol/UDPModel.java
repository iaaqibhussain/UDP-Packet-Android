package com.kodesnippets.aaqib.remotecontrol;


import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by silen on 8/27/2017.
 */

public class UDPModel implements Runnable {
    /**
     * Default port is 8080.
     */
    int port = 8080;
    /**
     * Default Host address is the loop back address.
     */
    String hostAddress = "127.0.0.1";
    /**
     * Message to sent.
     */
    String message;
    /**
     * Delay after to be set after each sequence. this is optional.
     */
    String delay = null;

    private DatagramSocket udpSocket = null;

    /**
     * Sending message on a different thread to avoid UI blockage.
     */
    @Override
    public void run() {
        try {
            if (udpSocket == null) {
                udpSocket = new DatagramSocket();
            }
            InetAddress listenerAddress = InetAddress.getByName(hostAddress);
            String newMessage = delay.equals("") ? message.concat("\n") : message.concat("\n") + "a " + delay + " ms pause\n";
            byte[] buffer = (newMessage).getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, listenerAddress, port);
            udpSocket.send(packet);
        } catch (SocketException e) {
            Log.e("Udp:", "Socket Error:", e);
        } catch (IOException e) {
            Log.e("Udp Send:", "IO Error:", e);
        }

    }
    /**
     * Enabling the broadcast.
     */
    public void enableBroadcast() {
        if (udpSocket != null) {
            try {
                udpSocket.setBroadcast(true);
            } catch (SocketException e) {
                Log.e("Udp:", "Socket Error:", e);
            }

        }
    }

    /**
     * Closing the the Socket.
     */
    public void close() {
        if (udpSocket != null) {
            udpSocket.close();
        }
    }
}
