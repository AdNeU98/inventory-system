package com.csye7374.inventory.async.command;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPFacade {

    int port = 1234;

    public UDPFacade() {

    }

    public void Server() throws IOException {
        DatagramSocket ds = new DatagramSocket(port);
        byte[] recieve = new byte[10000];

        DatagramPacket dpPacket;
        boolean run = true;
//        System.out.println("***** Server Listening on Port ***********");
        while (run) {
            dpPacket = new DatagramPacket(recieve, recieve.length);

            ds.receive(dpPacket);

//            System.out.print("\n***** Data sent by Client *****\n" + data(recieve));
            System.out.println();

            if (data(recieve).toString().equalsIgnoreCase("stop")) {
//                System.out.println("Exiting");
                run = false;
            }
            recieve = new byte[10000];

        }
        ds.disconnect();
        ds.close();
    }

    private static StringBuilder data(byte[] a) {
        if (a == null) {
            return null;
        }
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    public void Client(String msg) throws IOException {
        Scanner sc = new Scanner(System.in);
        String inp = "NO message";
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        inp = msg;
        byte[] buf = inp.getBytes();

        DatagramPacket dpSend = new DatagramPacket(buf, buf.length, ip, port);

        ds.send(dpSend);

        byte[] stop = "stop".getBytes();

        DatagramPacket stopSignal = new DatagramPacket(stop, stop.length, ip, port);

        ds.send(stopSignal);

        ds.disconnect();
        sc.close();
        ds.close();
    }
}
