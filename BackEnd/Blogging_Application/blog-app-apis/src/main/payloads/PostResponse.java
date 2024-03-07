package com.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// when we are sending Response to Client we send whole 
// info like pageNumber, pageSize, totalElements, totalPages, lastPage, and content

//"pageNumber": 1,
//"pageSize": 5,
//"totalElements": 5,
//"totalPages": 2,
//"lastPage": true
// to send this response to client we created PostResponse
@NoArgsConstructor
@Getter
@Setter

public class PostResponse {
	
	private List<PostDTO> content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private int totalElements;
	
	private int totalPages;
	
	// is current page is last page if true then true
	private boolean lastPage;

}
