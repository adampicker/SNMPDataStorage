package com.netizen.datastore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MibDTO {

    String oid;

    String unit;

    String description;

    String telnetShortcut;
}
