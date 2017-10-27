/**
 * v1 rest controller
 * 
 * @author Matt Day
 */
package io.pivotal.mday.examples.googledemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.translate.Language;

import io.pivotal.mday.examples.googledemo.model.SentimentModel;
import io.pivotal.mday.examples.googledemo.model.TranslateModel;
import io.pivotal.mday.examples.googledemo.services.LanguageServices;

@RestController()
@RequestMapping("/v1")
public class ControllerV1 {

	LanguageServices services;

	public ControllerV1(LanguageServices services) {
		this.services = services;
	}

	@GetMapping("/hello-world")
	public TranslateModel helloWorld() throws Exception {
		return services.translate("en", "fr", "Hello world!");
	}

	@GetMapping("/translate/{source}/{destination}/{text}")
	public TranslateModel translate(@PathVariable("source") String source,
			@PathVariable("destination") String destination, @PathVariable("text") String text) {
		return services.translate(source, destination, text);
	}

	@GetMapping("/sentiment/{text}")
	public SentimentModel sentiment(@PathVariable("text") String text) {
		return services.sentiment(text);
	}

	@GetMapping("/languages")
	public List<Language> getLanguages() {
		return services.getLanguages();
	}

	@GetMapping("/languages/{language}")
	public List<Language> getLanguageSupport(@PathVariable("language") String language) {
		return services.getLanguages(language);
	}

}