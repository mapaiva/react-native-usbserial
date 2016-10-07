'use strict';

import {
  Platform,
  NativeModules
} from 'react-native';

const UsbSerialModule = NativeModules.UsbSerial;

class UsbSerial {
    constructor() {

        if (Platform.OS != 'android') {
            throw 'Unfortunately only android is supported';
        }
    }

    getDeviceListAsync() {
        return UsbSerial.getDeviceListAsync();
    }

    openDeviceAsync(deviceObject = {}) {
        return UsbSerial.openDeviceByProductIdAsync(deviceObject);
    }
}

module.exports = UsbSerial;
