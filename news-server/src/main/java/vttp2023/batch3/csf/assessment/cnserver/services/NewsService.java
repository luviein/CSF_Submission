package vttp2023.batch3.csf.assessment.cnserver.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.nodes.Tag;

import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
import vttp2023.batch3.csf.assessment.cnserver.repositories.ImageRepository;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private ImageRepository imageRepo;

	@Autowired
	private NewsRepository newsRepo;
	
	Map<String, Integer> tagMap = new HashMap<>();
	
	
	// TODO: Task 1
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns the news id
	public String postNews(/* Any number of parameters */MultipartFile file) throws IOException {
		return this.imageRepo.saveImage(file);
	}
	 
	// TODO: Task 2
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of tags and their associated count
	public List<TagCount> getTags(/* Any number of parameters */Integer time) {
		List<TagCount> totalTags = new LinkedList<>();
		List<Document> topTags = this.newsRepo.getTagAndTime(time);


		for(Document tagDoc : topTags) {
			List<String> tags = tagDoc.getList("tags", String.class);
			for(String tag : tags) {
				var newCount = new TagCount(tag, 1);
				totalTags.add(newCount);
			}

		}
		return totalTags;
	}

	// TODO: Task 3
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of news
	public List<News> getNewsByTag(/* Any number of parameters */) {
		return new LinkedList<>();
	}
	
}
