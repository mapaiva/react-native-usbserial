'use strict';

import {
  Platform,
  NativeModules
} from 'react-native';
import UsbSerialDevice from './UsbSerialDevice';

const UsbSerialModule = NativeModules.UsbSerial;

class UsbSerial {
    constructor() {

        if (Platform.OS != 'android') {
            throw 'Unfortunately only android is supported';
        }
    }

    getDeviceListAsync() {
        return UsbSerialModule.getDeviceListAsync();
    }

    openDeviceAsync(deviceObject = {}) {
        return UsbSerialModule.openDeviceAsync(deviceObject).then((usbSerialDevNativeObject) => {
            return new UsbSerialDevice(UsbSerialModule, usbSerialDevNativeObject);
        });
    }
}

module.exports = UsbSerial;
