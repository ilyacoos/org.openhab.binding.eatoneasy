package org.openhab.binding.eatoneasy.easyAPI;

public class run {

    public static void main(String[] args) throws Exception {
        Device dev1 = new Device(6, 9600);

        Device.setTimeout(100);

        System.out.println(Device.getTimeout());

        dev1.connect();

        byte[] data = dev1.readValue(2, 3, 2, 0);
        System.out.printf("MC_Read_Object_Value(I) => hex %02X%02X \n", data[1], data[0]);

        dev1.disconnect();
    }

}
