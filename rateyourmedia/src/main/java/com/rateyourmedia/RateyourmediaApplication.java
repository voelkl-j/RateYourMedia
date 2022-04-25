package com.rateyourmedia;

import com.rateyourmedia.rym_entity.AccountType;
import com.rateyourmedia.rym_entity.User;
import com.rateyourmedia.rym_service.UserService;
import com.rateyourmedia.rym_web.DiscussionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class RateyourmediaApplication implements ApplicationRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private DiscussionController discussionController;

	//ApiKey für Amin's MyChat Schnittstelle
	//Falls nicht mehr aktuell sein sollte:
	//Bei MyChat auf der Seite mit Benutzername:RateYourMedia Passwort:ratemediapw einloggen
	//Anschließend unter Settings einen neuen ApiKey generieren, und dann der Applikation via Umgebungsvariable übergeben
	@Value("#{environment.apiKey}")
	String apiKey;

	public static void main(String[] args) {
		SpringApplication.run(RateyourmediaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//Bei MyChat eine Conversation starten, d.h. nötige Informationen für das Discussion Forum einholen.
		discussionController.creatingConversation(apiKey);
		try {
			userService.getUserByUsername("AdminRYM");
		} catch (Exception e){
			User admin= new User();
			admin.setUsername("AdminRYM");
			admin.setPassword("secret");
			admin.setFirst_name("RYM");
			admin.setSurname("Admin");
			admin.setDate_of_birth(new Date());
			admin.setAccountType(AccountType.ADMIN);
			userService.registerUser(admin);
		}
		try {
			userService.getUserByUsername("TestUser");
		} catch (Exception e){
			User testuser= new User();
			testuser.setUsername("TestUser");
			testuser.setPassword("secret");
			testuser.setFirst_name("Dude");
			testuser.setSurname("Test");
			testuser.setDate_of_birth(new Date());
			testuser.setAccountType(AccountType.STANDARD);
			userService.registerUser(testuser);
		}
	}
}
