package com.bmateam.reactnativeusbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.HashMap;

public class ReactUSBSerialModule extends ReactContextBaseJavaModule {

    public ReactUSBSerialModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "USBSerial";
    }

    @ReactMethod
    public void getDeviceList(Promise promise) {
        //UsbSerialDevice serial = UsbSerialDevice.createUsbSerialDevice(device, connection);

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
}
