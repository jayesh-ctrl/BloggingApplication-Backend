package com.blog.payloads;

import java.util.List;

import com.blog.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalElement;
    private int totalPages;

    private boolean lastPage;
}
