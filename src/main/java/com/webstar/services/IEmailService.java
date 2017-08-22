package com.webstar.services;

public interface IEmailService
{
  void sendMail(String toEmail, String subject, String message, String fromEmail);
}
