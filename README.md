# email-helper
Email Helper client to send the emails using different providers

## Example code
```Java
EmailSender emailSender = EmailHelperBuilder.buildSender(new SendgridCredentials("username", "password"));
emailSender.sendEmail(new Sender("sender@email.com"), new Receiver("receiver@email.com"), new EmailMessage("Hello this is test message", "Test Message"));
