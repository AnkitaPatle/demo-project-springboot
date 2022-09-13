package com.hcl.demoproject.clientEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    @JsonProperty
    private String companyName;

    @JsonProperty
    private String clientName;
}
