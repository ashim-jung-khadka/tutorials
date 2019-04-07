package com.ashim.pattern.observer;

//The Observers update method is called when the Subject changes

@FunctionalInterface
public interface Observer {

	public void update(double ibmPrice, double aaplPrice, double googPrice);

}