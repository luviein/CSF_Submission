package vttp2023.batch3.csf.assessment.cnserver.controllers;

import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.NewsInput;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;
import vttp2023.batch3.csf.assessment.cnserver.services.NewsService;

@RestController
@RequestMapping()
@CrossOrigin
public class NewsController {

	@Autowired
	private NewsService svc;

	@Autowired
	private NewsRepository newsRepo;
	// TODO: Task 1
	// @PostMapping(path="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	// public ResponseEntity<String> postNews( @RequestPart(name = "title") String title,
	// 	// @RequestParam(name = "file1")  MultipartFile file,
	// 	@RequestPart(name = "description") String description
	// 	// @RequestPart(name = "tags") List<String> tagList
	// 	) {
	// 	System.out.println("request body >>>>>" + title);
	// 	return null;
	// }

	
	@PostMapping(path="/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<String> postNews( @ModelAttribute NewsInput input
		) throws IOException {

		try{
		// System.out.println("title >>>>>>"+input.getTitle());

		News news = new News();
		news.setDescription(input.getDescription());
		news.setTitle(input.getTitle());
		long time = System.currentTimeMillis();
		news.setPostDate(time);

		long minutes =  ((time / 1000)  / 60);
		long timeNow = ((System.currentTimeMillis()/1000)/60);

		System.out.println("min >>>>>"+ (minutes - timeNow));

		

		news.setTags(input.getTags());
		System.out.println(input.getTags());

		String id = this.svc.postNews(input.getPhoto());
		news.setId(id);
		news.setImage("https://vttpcsfbucket.sgp1.digitaloceanspaces.com/"+id);
		System.out.println("news model >>>>>" + news);
		System.out.printf("Saving to S3: %s\n", id);
		this.newsRepo.postComment(news);
		JsonObject resp = Json.createObjectBuilder()
			.add("newsId", id)
			.build();
		return ResponseEntity.ok(resp.toString());
		} catch (IOException ex) {
		JsonObject resp = Json.createObjectBuilder()
			.add("error", ex.getMessage())
			.build();
		return ResponseEntity.status(500)
			.body(resp.toString());
		}
    
		// System.out.println("request body >>>>>" + input);

	}
	

	// TODO: Task 2
	@GetMapping(path="/getTopTags")
	public ResponseEntity<String> getTopTags (Integer time) {
		try{
			List<TagCount> tagCountList = this.svc.getTags(time);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}


	// TODO: Task 3

}
