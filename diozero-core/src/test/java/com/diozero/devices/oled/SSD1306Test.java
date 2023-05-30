package com.diozero.devices.oled;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import com.diozero.api.I2CDevice;
import com.diozero.devices.oled.SsdOledCommunicationChannel.I2cCommunicationChannel;
import com.diozero.util.Hex;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

/**
 * Test the setup and output of an SSD1306 - the data was captured via the Adafruit CircuitPython libraries.
 */
class SSD1306Test {
    private final List<Byte> testBuffer = new ArrayList<>();
    private final I2CDevice mockDevice = mock(I2CDevice.class);

    @BeforeEach
    void setUp() {
        testBuffer.clear();
        doAnswer((Answer<Void>)invoke -> {
            for (Object argument : invoke.getArguments()) {
                testBuffer.add((byte)argument);
            }

            return null;
        }).when(mockDevice).writeBytes(any(byte[].class));
    }

    @Test
    void initializtionForShortDisplayAndI2C() {
        byte[] commandsExpected = new byte[] {
                (byte)0x80, (byte)0xae

                ,(byte)0x80, (byte)0xd5 // disp clock divisor
                ,(byte)0x80, (byte)0x80

                ,(byte)0x80, (byte)0xa8 // mux ratio = height - 1
                ,(byte)0x80, 0x1f

                ,(byte)0x80, (byte)0xd3 // disp offset
                ,(byte)0x80, 0x00
                ,(byte)0x80, 0x40       // display start line

                ,(byte)0x80, (byte)0x8d // charge pump
                ,(byte)0x80, 0x14       // 0x10 if self.external_vcc else 0x14

                ,(byte)0x80, (byte)0x20 // page addressing mode
                ,(byte)0x80, 0x00
                ,(byte)0x80, (byte)0xa1 // segment remap (or with 1)
                ,(byte)0x80, (byte)0xc8 // comm out dir or with 8

                ,(byte)0x80, (byte)0xda // com pin cfg
                ,(byte)0x80, 0x02   // 0x02 if self.width > 2 * self.height else 0x12

                ,(byte)0x80, (byte)0x81 // set constrast
                ,(byte)0x80, (byte)0xff // all the way on

                ,(byte)0x80, (byte)0xd9 // precharge
                ,(byte)0x80, (byte)0xf1 // 0x22 if self.external_vcc else 0xF1

                ,(byte)0x80, (byte)0xdb // vcomm deselect
                ,(byte)0x80, 0x30       // 0x30,  # 0.83*Vcc  # n.b. specs for ssd1306 64x32 oled screens imply this should be 0x40

                ,(byte)0x80, (byte)0xa4 // entire on
                ,(byte)0x80, (byte)0xa6 // normal, not inverted

                ,(byte)0x80, (byte)0xad // iref
                ,(byte)0x80, 0x30

                ,(byte)0x80, (byte)0xaf // display on
        };

        new SSD1306(new I2cCommunicationChannel(mockDevice), SSD1306.Height.SHORT);
        assertFalse(testBuffer.isEmpty());
        byte[] actual = fromBuffer();
        assertArrayEquals(commandsExpected, actual);
    }

    @Test
    void threeDotsShortDisplayAndI2C() {
        byte[] dataExpected = new byte[] {
                // this is the "home" prefix
                (byte)0x80, 0x21,
                (byte)0x80, 0x00,
                (byte)0x80, 0x7f,
                (byte)0x80, 0x22,
                (byte)0x80, 0x00,
                (byte)0x80, 0x03,

                // this is the image
                0x40, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, (byte)0x80
        };

        // initialize the display and re-clear the buffer
        SSD1306 display = new SSD1306(new I2cCommunicationChannel(mockDevice), SSD1306.Height.SHORT);
        testBuffer.clear();
        display.setPixel(0,0,true);
        display.setPixel(64,16,true);
        display.setPixel(127, 31, true);
        display.display();

        assertArrayEquals(dataExpected, fromBuffer());
    }

    byte[] fromBuffer() {
        byte[] response = new byte[testBuffer.size()];
        for (int i = 0; i < testBuffer.size(); i++) {
            response[i] = testBuffer.get(i);
        }
        return response;
    }

    void printDiffs(byte[] expected, byte[] actual) {
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) System.out.printf("Index %d - %s vs %s%n", i,
                                                                    Hex.encode(expected[i]),
                                                                    Hex.encode(actual[i]));
        }

    }
}
