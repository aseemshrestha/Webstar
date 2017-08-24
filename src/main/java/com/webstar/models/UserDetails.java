package com.webstar.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table( indexes = { @Index( name = "IDX_USER_DETAILS", columnList = "email" ) } )
public class UserDetails
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;

    @Size( min = 1, max = 100, message = "required." )
    private String firstName;

    @Size( min = 1, max = 100, message = "required." )
    private String lastName;

    @Size( min = 1, max = 100, message = "required." )
    private String email;

    private String phone;

    @Size( min = 1, max = 100, message = "required." )
    private String password;

    @Size( min = 1, max = 100, message = "required." )
    private String passwordConfirm;

    @Temporal( TemporalType.TIMESTAMP )
    private Date registrationDate;

    private int userStatus;

    @Temporal( TemporalType.TIMESTAMP )
    private Date lastLoggedIn;

    private String ipAddress;

    private String role;

    private String resetToken;

    public UserDetails()
    {}

    public UserDetails(String firstName, String lastName, String email, String phone, String password,
        Date registrationDate, String ipAddress)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.registrationDate = registrationDate;
        this.ipAddress = ipAddress;
    }

    @PrePersist
    protected void onCreate()
    {

        if (registrationDate == null) {
            registrationDate = new Date();
        }
    }

    public Long getId()
    {

        return Id;
    }

    public void setId(Long Id)
    {

        this.Id = Id;
    }

    public String getFirstName()
    {

        return firstName;
    }

    public void setFirstName(String firstName)
    {

        this.firstName = firstName;
    }

    public String getLastName()
    {

        return lastName;
    }

    public void setLastName(String lastName)
    {

        this.lastName = lastName;
    }

    public String getEmail()
    {

        return email;
    }

    public void setEmail(String email)
    {

        this.email = email;
    }

    public String getPhone()
    {

        return phone;
    }

    public void setPhone(String phone)
    {

        this.phone = phone;
    }

    public String getPassword()
    {

        return password;
    }

    public void setPassword(String password)
    {

        this.password = password;
    }

    public Date getRegistrationDate()
    {

        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate)
    {

        this.registrationDate = registrationDate;
    }

    public String getIpAddress()
    {

        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {

        this.ipAddress = ipAddress;
    }

    public int getUserStatus()
    {

        return userStatus;
    }

    public void setUserStatus(int userStatus)
    {

        this.userStatus = userStatus;
    }

    public Date getLastLoggedIn()
    {

        return lastLoggedIn;
    }

    public void setLastLoggedIn(Date lastLoggedIn)
    {

        this.lastLoggedIn = lastLoggedIn;
    }

    public String getPasswordConfirm()
    {

        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordconfirm)
    {

        this.passwordConfirm = passwordconfirm;
    }

    public String getRole()
    {

        return role;
    }

    public void setRole(String role)
    {

        this.role = role;
    }

    public String getResetToken()
    {

        return resetToken;
    }

    public void setResetToken(String resetToken)
    {

        this.resetToken = resetToken;
    }

    @Override
    public int hashCode()
    {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = prime * result + ((lastLoggedIn == null) ? 0 : lastLoggedIn.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
        result = prime * result + ((resetToken == null) ? 0 : resetToken.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + userStatus;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDetails other = (UserDetails)obj;
        if (Id == null) {
            if (other.Id != null)
                return false;
        } else if (!Id.equals(other.Id))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (ipAddress == null) {
            if (other.ipAddress != null)
                return false;
        } else if (!ipAddress.equals(other.ipAddress))
            return false;
        if (lastLoggedIn == null) {
            if (other.lastLoggedIn != null)
                return false;
        } else if (!lastLoggedIn.equals(other.lastLoggedIn))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (passwordConfirm == null) {
            if (other.passwordConfirm != null)
                return false;
        } else if (!passwordConfirm.equals(other.passwordConfirm))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (registrationDate == null) {
            if (other.registrationDate != null)
                return false;
        } else if (!registrationDate.equals(other.registrationDate))
            return false;
        if (resetToken == null) {
            if (other.resetToken != null)
                return false;
        } else if (!resetToken.equals(other.resetToken))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (userStatus != other.userStatus)
            return false;
        return true;
    }
}
