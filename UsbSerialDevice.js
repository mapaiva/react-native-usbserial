
export default class UsbSerialDevice() {

    constructor(UsbSerialModule, UsbSerialDevNativeObject) {
        this.UsbSerialModule = UsbSerialModule;
        this.UsbSerialDevNativeObject = UsbSerialDevNativeObject;
    }

    write(value = "") {
        return UsbSerialModule.writeInDeviceAsync(UsbSerialDevNativeObject.id, value);
    }
}
