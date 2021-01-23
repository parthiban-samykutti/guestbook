package com.galvanize.guestbook;

import com.galvanize.guestbook.bean.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GuestBookController {

    private List<Post> postList = new ArrayList<>();

    @PostConstruct
    public void init(){
        Post post = new Post();
        post.setComment("happy birthday");
        post.setName("parthiban");
        postList.add(post);
        Post post1 = new Post();
        post1.setComment("happy birthday");
        post1.setName("Balaji");
        postList.add(post);
    }

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
