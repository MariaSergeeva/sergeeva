package ru.stqa.pft.mantis.model;

import java.util.List;

public class MailMessage {
  public String to;
  public String text;

  public MailMessage(String to, String text){
    this.to = to;
    this.text = text;
  }
  public List<MailMessage> withoutMessages(List<MailMessage> mailMessages){
    List<MailMessage> messages = (List<MailMessage>) this;
    messages.removeAll(mailMessages);
    return messages;
  }
}
