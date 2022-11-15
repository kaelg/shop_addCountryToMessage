package pl.waw.great.shop.model.dto;

import javax.validation.constraints.NotBlank;

public class MessageDto {

    @NotBlank(message = "{titleNotBlank}")
    private String title;
    @NotBlank(message = "{textNotBlank}")
    private String body;
    @NotBlank(message = "{cityNotBlank}")
    private String city;
    @NotBlank(message = "{emailNotBlank}")
    private String email;
    @NotBlank(message = "{countryNotBlank}")
    private String country;

    public MessageDto(String title, String body, String city, String email, String country) {
        this.title = title;
        this.body = body;
        this.city = city;
        this.email = email;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
