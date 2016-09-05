package com.bmateam.reactnativeusbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReactUSBSerialModule extends ReactContextBaseJavaModule {

    private USBSerialDevice tmpUSBSerialDevice;

    public ReactUSBSerialModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "USBSerial";
    }

    @ReactMethod
    public void getDeviceListAsync(Promise promise) {

        try {
            ReactApplicationContext reactContext = getReactApplicationContext();
            UsbManager usbManager = (UsbManager) reactContext.getSystemService(reactContext.USB_SERVICE);

            HashMap<String, UsbDevice> usbDevices = usbManager.getDeviceList();
            WritableArray deviceArray = Arguments.createArray();

            for (String key: usbDevices.keySet()) {
                UsbDevice device = usbDevices.get(key);
                WritableMap map = Arguments.createMap();

                map.putString("name", device.getDeviceName());
                map.putInt("deviceId", device.getDeviceId());
                map.putInt("productId", device.getProductId());
                map.putInt("vendorId", device.getVendorId());
                map.putString("deviceName", device.getDeviceName());

                deviceArray.pushMap(map);
            }

            promise.resolve(deviceArray);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void openDeviceByProductIdAsync(int productId, Promise promise) {
        ReactApplicationContext reactContext = getReactApplicationContext();
        UsbManager manager = (UsbManager) reactContext.getSystemService(reactContext.USB_SERVICE);

        List<UsbSerialDriver> availableDrivers = (List<UsbSerialDriver>) UsbSerialProber
                .getDefaultProber()
                .findAllDrivers(manager);

        if (availableDrivers.isEmpty()) {
            promise.reject(new Exception("No available drivers to find devices"));

            return;
        }

        UsbSerialDriver driver = null;

        for (UsbSerialDriver drv : availableDrivers) {

            if (drv.getDevice().getProductId() == productId) {
                driver = drv;
                break;
            }
        }

        if (driver == null) {
            promise.reject(new Exception(String.format("No driver found for productId '%s'", productId)));

            return;
        }

        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());

        if (connection == null) {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            promise.reject(new Exception("No connections available"));

            return;
        }

        // Read some data! Most have just one port (port 0).
        UsbSerialPort port = driver.getPorts().get(0);

        try {
            port.open(connection);
            port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

            this.tmpUSBSerialDevice = new USBSerialDevice(port);

            UsbDevice device = driver.getDevice();
            WritableMap map = Arguments.createMap();

            map.putString("name", device.getDeviceName());
            map.putInt("deviceId", device.getDeviceId());
            map.putInt("productId", device.getProductId());
            map.putInt("vendorId", device.getVendorId());
            map.putString("deviceName", device.getDeviceName());

            promise.resolve(map);
        } catch (IOException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void writeAsync(byte b, Promise promise) {

        if (this.tmpUSBSerialDevice != null) {
            this.tmpUSBSerialDevice.write(b, promise);
        } else {
            promise.reject(new Exception("No device opened"));
        }
    }
}
