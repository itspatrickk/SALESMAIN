package com.restapi.cicd.entity;



import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sales_user_mst")
public class SalesUser {

    @Id
    @SequenceGenerator(name = "id_seqno_generator", sequenceName = "idseqno", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seqno_generator")
    private Long Id;
    private String username;
    private String password;
    @Column(name = "date_created")
    private Date dateCreated;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
