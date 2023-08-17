package com.intexsoft.webshop.messagecommon.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseCommand {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime createdAt;

    protected BaseCommand() {
        createdAt = LocalDateTime.now();
    }
}
