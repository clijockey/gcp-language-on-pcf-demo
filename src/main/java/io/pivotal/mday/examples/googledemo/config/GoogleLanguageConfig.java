/**
 * Google natural language API configuration
 * 
 * Configures Google authentication within Pivotal Cloud foundry for Google Translate and Natural Language APIs
 * 
 * @author Matt Day
 */
package io.pivotal.mday.examples.googledemo.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.LanguageServiceSettings;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

@Configuration
public class GoogleLanguageConfig {

	// Get Google credentials from PCF runtime (VCAP_SERVICES)
	@Bean
	public GoogleCredentials credential() throws IOException {
		String env = System.getenv("VCAP_SERVICES");
		String privateKeyData = new JSONObject(env).getJSONArray("google-ml-apis").getJSONObject(0)
				.getJSONObject("credentials").getString("PrivateKeyData");
		InputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(privateKeyData));
		return GoogleCredentials.fromStream(stream);
	}

	// Configure language API to use provided Google credentials
	@Bean
	public LanguageServiceSettings language(GoogleCredentials credentials) throws IOException {
		return LanguageServiceSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(credentials))
				.build();
	}

	// Instance of Google's language API
	@Bean
	public LanguageServiceClient languageClient(LanguageServiceSettings language) throws IOException {
		return LanguageServiceClient.create(language);
	}

	// Configure translate API to use provided Google credentials
	@Bean
	public TranslateOptions translateOptions(GoogleCredentials credentials) throws IOException {
		return TranslateOptions.newBuilder().setCredentials(credentials).build();
	}

	// Instance of Google's translate API
	@Bean
	public Translate translateClient(TranslateOptions options) throws IOException {
		return options.toBuilder().build().getService();
	}
}
