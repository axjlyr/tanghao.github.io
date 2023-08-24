/*
 * 陈昱蓉 拥有本软件版权 并保留所有权利。
 */
package com.wms.common;

/**
 * @author chenyurong
 * @version 1.0
 */

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MACTest01 {

    public static void main(String[] args) throws SocketException {
        StringBuilder sb = new StringBuilder();
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        byte[] mac = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            if (netInterface.isLoopback() || netInterface.isVirtual() || netInterface.isPointToPoint() || !netInterface.isUp()) {
                continue;
            } else {
                mac = netInterface.getHardwareAddress();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "\n"));
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }
}

