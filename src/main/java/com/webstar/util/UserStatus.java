package com.webstar.util;

public enum UserStatus
{

    ACTIVE(Constants.ACTIVE),
    PASSIVE(Constants.PASSIVE),
    DELETED(Constants.DELETED),
    BLOCKED(Constants.BLOCKED);

    private int status;

    UserStatus(int status)
    {
        this.status = status;
    }

    public int getStatus()
    {
        return status;
    }
}
