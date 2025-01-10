/*
Author: Matt Farzaneh
Student number: 251370889

Description:
The Data class gets the data i.e the configuration and score
*/
public class Data {
	
	private String config;
	private int score;
	
	// Constructor
	public Data(String config, int score) {
		this.config = config;
		this.score = score;
	}
	
	// get configuration
	public String getConfiguration() {
		return config;
	}
	
	// get score
	public int getScore() {
		return score;
	}
	
}
