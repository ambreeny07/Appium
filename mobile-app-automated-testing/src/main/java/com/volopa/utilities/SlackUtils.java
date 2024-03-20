package com.volopa.utilities;

import java.io.IOException;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Message;
import com.volopa.base.PropertiesReader;

public class SlackUtils {
	
	static Slack slack = Slack.getInstance();
	public static void SendSlackMessage(String emailBody) throws IOException, SlackApiException {
		ChatPostMessageResponse response = slack.methods(PropertiesReader.getPropertyValue("slack_token")).chatPostMessage(req -> req
				  .channel(PropertiesReader.getPropertyValue("channel_id"))
				  .text(emailBody));
				if (response.isOk()) {
				  Message postedMessage = response.getMessage();
				  System.out.println("Message Posted to Slack: \n"+postedMessage.getText());
				} else {
				  String errorCode = response.getError(); // e.g., "invalid_auth", "channel_not_found"
				  System.out.println("Slack Error: "+errorCode);
				}
	}

}
