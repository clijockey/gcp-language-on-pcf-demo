/**
 * Model for our own sentiment analysis responses
 * 
 * @author Matt Day
 */
package io.pivotal.mday.examples.googledemo.model;

public class SentimentModel {
	private float sentiment;
	private String text;

	public float getSentiment() {
		return sentiment;
	}

	public void setSentiment(float sentiment) {
		this.sentiment = sentiment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
