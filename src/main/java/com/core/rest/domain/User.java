package com.core.rest.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 2, message = "Name is too short")
    @ApiModelProperty(notes="Min size = 2")
    private String name;

    @Past
    @ApiModelProperty(notes="Should be in the past", example = "30/11/2017")
    private Date birthday;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    public User() {
    }

    public User(Long id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
