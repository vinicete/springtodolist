package fipp.revisao2bim.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class CreateTodoItemDto {
    @JsonProperty("td_content")
    private String content;
    
    @JsonProperty("td_date")
    private LocalDate date;
    
    @JsonProperty("td_status")
    private String status;
    
    @JsonProperty("user_id")
    private UserDto user;

    public static class UserDto {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
