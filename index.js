'use strict';

import {
  Platform,
  NativeModules
} from 'react-native';

const USBSerialModule = NativeModules.USBSerial;

class USBSerial {
    constructor() {

        if (Platform.OS != 'android') {
            throw 'Unfortunately only android is supported';
        }
    }

    getDeviceListAsync() {
        return USBSerialModule.getDeviceListAsync();
    }

    openDeviceByProductIdAsync(productId) {
        return USBSerialModule.openDeviceByProductIdAsync(productId);
    }

    writeAsync(value) {
        return USBSerialModule.writeAsync(value);
    }
}

module.exports = USBSerial;
