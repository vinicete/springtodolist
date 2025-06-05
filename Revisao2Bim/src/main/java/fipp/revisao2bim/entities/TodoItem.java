package fipp.revisao2bim.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "todoitem")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "td_id")
    private Integer id;
    @Column(name = "td_content")
    @JsonProperty("td_content")
    private String content;
    @Column(name = "td_date")
    @JsonProperty("td_date")
    private LocalDate date;
    @Column(name = "td_status")
    @JsonProperty("td_status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id", nullable = false)
    @JsonProperty("user_id")
    private User user_id;

    public TodoItem(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
}
