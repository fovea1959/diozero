package com.diozero.internal.provider.voodoospark;

/*-
 * #%L
 * Organisation: mattjlewis
 * Project:      Device I/O Zero - Voodoo Spark provider for Particle Photon
 * Filename:     VoodooSparkDigitalInputOutputDevice.java  
 * 
 * This file is part of the diozero project. More information about this project
 * can be found at http://www.diozero.com/
 * %%
 * Copyright (C) 2016 - 2020 mattjlewis
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import com.diozero.api.DeviceMode;
import com.diozero.api.DigitalInputEvent;
import com.diozero.api.PinInfo;
import com.diozero.internal.provider.AbstractInputDevice;
import com.diozero.internal.provider.GpioDigitalInputOutputDeviceInterface;
import com.diozero.internal.provider.voodoospark.VoodooSparkDeviceFactory.PinMode;
import com.diozero.util.RuntimeIOException;

public class VoodooSparkDigitalInputOutputDevice extends AbstractInputDevice<DigitalInputEvent>
implements GpioDigitalInputOutputDeviceInterface {
	private int gpio;
	private DeviceMode mode;
	private VoodooSparkDeviceFactory deviceFactory;

	public VoodooSparkDigitalInputOutputDevice(VoodooSparkDeviceFactory deviceFactory, String key, PinInfo pinInfo,
			DeviceMode mode) {
		super(key, deviceFactory);
		
		this.deviceFactory = deviceFactory;
		gpio = pinInfo.getDeviceNumber();
		
		setMode(mode);
	}

	@Override
	public boolean getValue() throws RuntimeIOException {
		return deviceFactory.getValue(gpio);
	}

	@Override
	public void setValue(boolean value) throws RuntimeIOException {
		deviceFactory.setValue(gpio, value);
	}

	@Override
	public int getGpio() {
		return gpio;
	}

	@Override
	public DeviceMode getMode() {
		return mode;
	}

	@Override
	public void setMode(DeviceMode mode) {
		deviceFactory.setPinMode(gpio, mode == DeviceMode.DIGITAL_INPUT ? PinMode.DIGITAL_INPUT : PinMode.DIGITAL_OUTPUT);
		this.mode = mode;
	}

	@Override
	protected void closeDevice() throws RuntimeIOException {
		// Nothing to do
	}
}
