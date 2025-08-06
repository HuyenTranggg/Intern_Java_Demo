package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Post;
import com.example.demo.service.N1ProblemDemoService;

@SpringBootApplication
public class MysqlDemoApplication implements CommandLineRunner {
    
    private final N1ProblemDemoService n1ProblemDemoService;
    @Autowired
    public MysqlDemoApplication(N1ProblemDemoService n1ProblemDemoService) {
    	this.n1ProblemDemoService = n1ProblemDemoService;
    }
	public static void main(String[] args) {
		SpringApplication.run(MysqlDemoApplication.class, args);
	}
	
	@Override
	public void run(String...arg) throws Exception{
		//DEMO VỀ N+1 QUERY
		// Setup dữ liệu mẫu 2 tác giả, 3 bài viết
		n1ProblemDemoService.setupData();
		// Minh họa vấn đề N+1
		List<Post> postsWithProblem = n1ProblemDemoService.getPostsTheBadWay();
		System.out.println("Thực thi vòng lặp để kích hoạt lazy/eager loading:");
		postsWithProblem.forEach(post -> System.out.println("  - Bài viết: " + post.getTitle() + ", Tác giả: " + post.getAuthor().getName()));
		// Minh họa giải pháp cho N+1
		System.out.println("\n--- 3. Minh họa GIẢI PHÁP cho N+1 (gọi hàm JOIN FETCH, chú ý log sql) ---");
		List<Post> postsWithSolution = n1ProblemDemoService.getPostsTheGoodWay();
		postsWithSolution.forEach(post -> System.out.println("  - Bài viết: " + post.getTitle() + ", Tác giả: " + post.getAuthor().getName()));
		
	}
}
