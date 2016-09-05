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
        return USBSerialModule.getDeviceList();
    }
}

module.exports = USBSerial;
