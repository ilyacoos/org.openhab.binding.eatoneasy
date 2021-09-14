package org.openhab.binding.eatoneasy.easyAPI;

public class run {

    public static void main(String[] args) throws Exception {
        long st = System.currentTimeMillis();
        Device dev1 = new Device(6);

        System.out.println("Created DLL: " + (System.currentTimeMillis() - st));

        byte bData[] = null;

        dev1.connect();
        System.out.println("Connected: " + (System.currentTimeMillis() - st));

        bData = dev1.readValue(0, Device.component.DIGITAL_OUTPUT_EXP__S1_S8, 2);
        System.out.println("Got S1-S8: " + (System.currentTimeMillis() - st));

        for (byte i : bData) {
            System.out.print(i & 0xFF);
            System.out.print(" ");
        }
        System.out.println("... print it: " + (System.currentTimeMillis() - st));

        bData = dev1.readValue(0, Device.component.BIT_MARKER__M1_M128, 2);
        System.out.println("");
        System.out.println("Got M1-M28: " + (System.currentTimeMillis() - st));

        for (byte i : bData) {
            System.out.print(i & 0xFF);
            System.out.print(" ");
        }
        System.out.println("... print it: " + (System.currentTimeMillis() - st));

        dev1.disconnect();
        System.out.println("Disconnected: " + (System.currentTimeMillis() - st));
    }

}
