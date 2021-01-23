package com.galvanize.guestbook.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VisitorEntry {
    private String name;
    private String comment;
}
