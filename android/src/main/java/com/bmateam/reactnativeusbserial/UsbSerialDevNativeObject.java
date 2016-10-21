package com.bmateam.reactnativeusbserial;

import com.facebook.react.bridge.WritableNativeMap;

public class UsbSerialDevNativeObject extends WritableNativeMap {

    public UsbSerialDevNativeObject(String id) {
        super();

        this.putString("id", id);
    }
}
