# react-native-usbserial
This wrapper implementation is totally based on the [usb-serial-for-android](https://github.com/mik3y/usb-serial-for-android) project. Thank you [mik3y](https://github.com/mik3y) for the awesome project.

## Installation
```
npm install react-native-usbserial --save
```

## Integrate module
To integrate `react-native-usbserial` with the rest of yout react app just execute:
```
react-native link react-native-usbserial
```

## Usage

```javascript
import { UsbSerial} from 'react-native-usbserial';

const usbs = new UsbSerial();

async function getDeviceAsync() {

    try {
        const deviceList = await usbs.getDeviceListAsync();
        const firstDevice = deviceList[0];
        
        console.log(firstDevice);

        if (firstDevice) {
            const usbSerialDevice = await usbs.openDeviceAsync(firstDevice);
            
            console.log(usbSerialDevice);
        }
    } catch (err) {
        console.warn(err);
    }
}

getDeviceAsync();
```
