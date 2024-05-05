package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements IModel<User>{
    private int Id;
    private String name;
    private String email;
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String email) {
        Id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String encryptMd5(String password) {
        String hash = "AJHFDKJBKDJHKAKJFIOughiahfiu";
        String updatePass = password+hash;
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(updatePass.getBytes());
            byte[] digest = md.digest();
            BigInteger num = new BigInteger(1, digest);
            sb.append(num.toString(16));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getTable() {
        return "User";
    }

    @Override
    public String beforeData() {
        return "{ id: " + this.Id + ", name: " + this.name + " }";
    }

    @Override
    public String afterData(User model) {
        return "{ id: " + model.Id + ", name: " + model.name + " }";
    }
}
