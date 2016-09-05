package com.bmateam.reactnativeusbserial;

import com.facebook.react.bridge.Promise;
import com.hoho.android.usbserial.driver.UsbSerialPort;

import java.io.IOException;

public class USBSerialDevice {
    private UsbSerialPort port;
    private static final int SERIAL_TIMEOUT = 1000;

    public USBSerialDevice(UsbSerialPort port) {
        this.port = port;
    }

    public void write(byte b, Promise promise) {

        if (port != null) {

            try {
                port.write(new byte[] { b }, SERIAL_TIMEOUT);

                promise.resolve(null);
            } catch (IOException e) {
                promise.reject(e);
            }

        } else {
            promise.reject(new Exception("No port present for the USBSerialDevice instance"));
        }
    }

    public void read(Promise promise) {

        if (port != null) {
            // TODO
            promise.resolve(null);
        } else {
            promise.resolve(new Exception("No port present for the USBSerialDevice instance"));
        }
    }
}
