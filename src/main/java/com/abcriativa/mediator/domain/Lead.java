package com.abcriativa.mediator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * This Represents a Lead. The first contact until it becomes a client.
 */
@Entity
@Table(name="LEADTB")
public class Lead {

    @Id
    private String email;

    private String name;

    private String phone;

    private String api;

    private String platform;

    private String tags;

    public Lead() {
    }

    public Lead(String email) {
        this.email = email;
    }

    public Lead(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public Lead(String email,
                String name,
                String phone,
                String api,
                String platform,
                String tags) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.api = api;
        this.platform = platform;
        this.tags = tags;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lead lead = (Lead) o;
        return Objects.equals(email, lead.email) && Objects.equals(name, lead.name)
                && Objects.equals(phone, lead.phone) && Objects.equals(api, lead.api)
                && Objects.equals(platform, lead.platform) && Objects.equals(tags, lead.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, phone, api, platform, tags);
    }

    @Override
    public String toString() {
        return "Lead{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", api='" + api + '\'' +
                ", platform='" + platform + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
