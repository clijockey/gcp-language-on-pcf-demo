/**
 * Easy-to-consume service point for all Google services
 * 
 * @author Matt Day
 */
package io.pivotal.mday.examples.googledemo.services;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.LanguageListOption;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.Translation;

import io.pivotal.mday.examples.googledemo.model.SentimentModel;
import io.pivotal.mday.examples.googledemo.model.TranslateModel;

@Service
public class LanguageServices {

	LanguageServiceClient languageClient;
	Translate translationClient;

	public LanguageServices(Translate translationClient, LanguageServiceClient languageClient) {
		this.languageClient = languageClient;
		this.translationClient = translationClient;
	}

	@Cacheable
	public TranslateModel translate(String source, String destination, String text) {
		// Translates some text into French
		Translation translation = translationClient.translate(text, TranslateOption.sourceLanguage(source),
				TranslateOption.targetLanguage(destination));
		TranslateModel m = new TranslateModel();
		m.setDesintationLanguage(destination);
		m.setSourceLanguage(source);
		m.setSourceText(text);
		m.setTranslatedText(translation.getTranslatedText());
		return m;
	}

	@Cacheable
	public SentimentModel sentiment(String text) {
		Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
		Sentiment sentiment = languageClient.analyzeSentiment(doc).getDocumentSentiment();
		SentimentModel m = new SentimentModel();
		m.setText(text);
		m.setSentiment(sentiment.getScore());
		return m;
	}

	@Cacheable
	public List<Language> getLanguages() {
		return translationClient.listSupportedLanguages();
	}

	@Cacheable
	public List<Language> getLanguages(String language) {
		LanguageListOption target = LanguageListOption.targetLanguage(language);
		return translationClient.listSupportedLanguages(target);
	}

}
