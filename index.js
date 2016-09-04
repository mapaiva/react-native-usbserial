'use strict';

import {
  Platform,
  NativeModules
} from 'react-native';

const USBSerialModule = NativeModules.USBSerial;

class USBSerial {
    constructor() {
        super();

        if (Platform.OS != 'android') {
            throw 'Unfortunately only android is supported';
        }
    }

    async getDeviceList() {
        let deviceList = await USBSerialModule.getDeviceList();

        return deviceList;
    }
}

modules.exports =
