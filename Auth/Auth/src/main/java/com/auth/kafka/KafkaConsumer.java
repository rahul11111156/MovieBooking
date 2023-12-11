package com.auth.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import com.auth.model.UserData;
import com.auth.userdata.UserDataService;
import com.google.gson.Gson;

@Configuration
@EnableKafka
public class KafkaConsumer {
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private UserDataService userdataService;
	
	@KafkaListener(topics = "nutritionapp", groupId = "nutritiongroup")
    public void consume(String user){
        System.out.println("Message Received = "+ user);
        UserData userdata=gson.fromJson(user, UserData.class);
       
        UserData result = userdataService.addUser(userdata);
        System.out.println("Stored data" + result.toString());
	}
}