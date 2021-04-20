package me.spring.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @NotEmpty
    private String email;

    @Setter
    @NotEmpty
    private String name;

    @Setter
    @NotNull
    private Long level;

    private String password;

    public boolean isAdmin() {
        return level >= 100L;
    }

    public boolean isActive() {
        return level > 0L;
    }

    public void deactivate() {
        level = 0L;
    }

    @JsonIgnore
    public String getAccessToken() {
        if (password == null) {
            return "";
        }
        return password.substring(0, 10);
    }
}
