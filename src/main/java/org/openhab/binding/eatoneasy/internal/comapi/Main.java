package org.openhab.binding.eatoneasy.internal.comapi;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IllegalArgumentException, UnsatisfiedLinkError, IOException {
        ComApiMock com = ComApiMock.connect(ComApiMock.DeviceType.EASY_800_MFD, 3, ComApiMock.BaudRate.BR_19200, 800, 1,
                null);

        // System.out.println(com.getValue(1, ComApiMock.ElementType.BIT_MARKER__M1_M96, 1));

        ComApiMock com2 = ComApiMock.connect(ComApiMock.DeviceType.EASY_800_MFD, 3, ComApiMock.BaudRate.BR_19200, 800,
                2, null);
        System.out.println(com.pDevices);

        System.out.println(Thread.currentThread().getId());
        // boolean[] ar = com.getValuesArray(1, ComApi.ElementType.BIT_MARKER__M1_M96);
        // for (int i = 0; i < ar.length; i++) {
        // System.out.println(i + ": " + String.valueOf(ar[i]));

        // }
        // byte b = 68;

        // System.out.println(b & 0x64);
        // System.out.println(String.valueOf((b & 64) == 64));
        // com.setValue(1, ComApiMock.ElementType.BIT_MARKER__M1_M96, 1, false);

        // System.out.println(com.getValue(1, ComApiMock.ElementType.BIT_MARKER__M1_M96, 1));

        com.disconnect(1, false);
    }

}
