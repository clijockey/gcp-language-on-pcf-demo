/**
 * Model for our own language translation responses
 * 
 * @author Matt Day
 */
package io.pivotal.mday.examples.googledemo.model;

public class TranslateModel {
	public String sourceLanguage;
	public String sourceText;
	public String desintationLanguage;
	public String translatedText;

	public String getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public String getSourceText() {
		return sourceText;
	}

	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}

	public String getDesintationLanguage() {
		return desintationLanguage;
	}

	public void setDesintationLanguage(String desintationLanguage) {
		this.desintationLanguage = desintationLanguage;
	}

	public String getTranslatedText() {
		return translatedText;
	}

	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}

}
