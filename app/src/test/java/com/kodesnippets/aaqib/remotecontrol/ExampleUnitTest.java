package com.kodesnippets.aaqib.remotecontrol;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void send_packet(){
        UDPModel udpModel = new UDPModel();
        udpModel.message = "start video1";
        udpModel.hostAddress = "192.168.1.108";
        udpModel.delay = "500";
        udpModel.port = 8080;
        new Thread(udpModel).start();
    }
}