package com.galvanize.guestbook.controller;

import com.galvanize.guestbook.model.VisitorEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/guestbook/entry")
@Slf4j
public class GuestBookController {

    private static final List<VisitorEntry> visitorEntries = new ArrayList<>();

    /**
     * Initial entry in the guestbook
     */
    @PostConstruct
    public void init() {
        visitorEntries.add(VisitorEntry.builder()
                .name("Parthiban")
                .comment("happy birthday")
                .build());
        visitorEntries.add(VisitorEntry.builder()
                .name("Balaji")
                .comment("happy birthday")
                .build());
    }

    /**
     * Creates a visitor entry to the guest book.
     *
     * @param visitorEntry
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEntry(@RequestBody VisitorEntry visitorEntry) {
        log.info("adding new enrty to guestbook: {}", visitorEntry);
        visitorEntries.add(visitorEntry);
    }

    /**
     * Find all the entries in guestbook.
     *
     * @return visitorEntries
     */
    @GetMapping
    public List<VisitorEntry> getAllEntries() {

        log.info("Getting all entries from guestbook: {}", visitorEntries);
        return visitorEntries;
    }


}
