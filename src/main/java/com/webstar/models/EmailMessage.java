package com.webstar.models;

public class EmailMessage
{
    private String messageTo;
    private String messageBody;
    private String messageFrom;

    public String getMessageTo()
    {
        return messageTo;
    }

    public void setMessageTo(String messageTo)
    {
        this.messageTo = messageTo;
    }

    public String getMessageBody()
    {
        return messageBody;
    }

    public void setMessageBody(String messageBody)
    {
        this.messageBody = messageBody;
    }

    public String getMessageFrom()
    {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom)
    {
        this.messageFrom = messageFrom;
    }

}
