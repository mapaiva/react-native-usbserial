
class UsbSerialDevice {

    constructor(UsbSerialModule, UsbSerialDevNativeObject) {
        this.UsbSerialModule = UsbSerialModule;
        this.id = UsbSerialDevNativeObject.id;
    }

    writeAsync(value = "") {
        return UsbSerialModule.writeInDeviceAsync(UsbSerialDevNativeObject.id, value);
    }
}

module.exports = UsbSerialDevice;
