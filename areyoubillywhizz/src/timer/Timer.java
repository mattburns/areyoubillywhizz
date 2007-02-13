package timer;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import parser.TimerCodeParser;
import parser.TimerCodeParser.TimerState;

public class Timer implements Runnable, SerialPortEventListener {
	
	private boolean shutdownFlag = false;
	
	static CommPortIdentifier portId1;

	static CommPortIdentifier portId2;

	InputStream inputStream;

	OutputStream outputStream;

	SerialPort serialPort1, serialPort2;

	Thread readThread;

	protected String divertCode = "10";

	static String TimeStamp;
	
	long jiffys;
	TimerState state = TimerState.OFF;
	
	TimerEventHandler timerEventHandler;

	public Timer(TimerEventHandler timerEventHandler) {
		try {
			this.timerEventHandler = timerEventHandler;
			portId1 = CommPortIdentifier.getPortIdentifier("COM1");
			TimeStamp = new java.util.Date().toString();
			serialPort1 = (SerialPort) portId1.open("ComControl", 2000);
			inputStream = serialPort1.getInputStream();
			serialPort1.addEventListener(this);
			serialPort1.notifyOnDataAvailable(true);
			serialPort1.setSerialPortParams(1200, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			serialPort1.setDTR(false);
			serialPort1.setRTS(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		readThread = new Thread(this);
		readThread.start();
	}

	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			StringBuffer readBuffer = new StringBuffer();
			int c;
			try {
				while ((c = inputStream.read()) != 10) {
					if (c != 13)
						readBuffer.append((char) c);
				}
				String scannedInput = readBuffer.toString();
				scannedInput = scannedInput.substring(
						scannedInput.length() - 7, scannedInput.length());

//			 System.out.println("scanned input received:" + scannedInput);
				// System.out.println("scanned input length:" +
				// scannedInput.length());
				if (TimerCodeParser.isValid(scannedInput)) {
					timerEventHandler.processScannedInput(scannedInput);
				}
				inputStream.close();
			} catch (IOException e) {
			}

			break;
		}
	}

	public void run() {
		try {
			if (shutdownFlag) {
				throw new InterruptedException();
			}
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}
	
	public void stop() {
		serialPort1.close();
		readThread.interrupt();
		readThread = null;
		inputStream = null;
	}

	public long getJiffys() {
		return jiffys;
	}

	public void setJiffys(long millis) {
		this.jiffys = millis;
	}

	public TimerState getState() {
		return state;
	}

	public void setState(TimerState state) {
		this.state = state;
	}
}
