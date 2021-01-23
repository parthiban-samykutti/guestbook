package com.galvanize.guestbook;

import com.galvanize.guestbook.bean.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GuestBookController {

    private List<Post> postList = new ArrayList<>();

    @PostMapping("/api/guestbook/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComentByGuestUser(@RequestBody Post post){
        postList.add(post);
    }

    @GetMapping("/api/getallposts")
    public List<Post> getAllPosts(){
        return postList;
    }



}
