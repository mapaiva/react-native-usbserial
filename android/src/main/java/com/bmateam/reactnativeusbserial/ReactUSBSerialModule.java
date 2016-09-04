package com.bmateam.reactnativeusbserial;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ReactUSBSerialModule extends ReactContextBaseJavaModule {

    public ReactUSBSerialModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "USBSerial";
    }

    @ReactMethod
    public String olokoBixu() {
        return "Vindo do Java olha a fera ai";
    }
}
