package lab5.general;

import java.util.Observable;

/**
* <p>
* The base state. Extended by simulation specific states.
* Keeps track of the time variable and "if the program is running" variable. 
* </p>
* @author Anton Lundmark, Elliot Johansson Fryklöf, Karolina Rucinska and Max Agnesund.  
*/
@SuppressWarnings("deprecation")
public class State extends Observable{
	
	public boolean isNotRunning = false;
	public double currentTime;
}
