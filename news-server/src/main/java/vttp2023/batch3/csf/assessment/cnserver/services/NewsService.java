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

import vttp2023.batch3.csf.assessment.cnserver.Utils.Utils;
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
	public List<TagCount> getTags(/* Any number of parameters */) {
		List<TagCount> tagCountList = new LinkedList<>();
		List<Document> tagCounts = this.newsRepo.getTagAndCount();
		for(Document tag : tagCounts) {
			var newCount = new TagCount(tag.getString("_id"), tag.getInteger("count"));
			tagCountList.add(newCount);
		}
		return tagCountList;
	}

	// TODO: Task 3
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of news
	public List<News> getNewsByTag(/* Any number of parameters */ String tag) {
		List<Document> docList = this.newsRepo.getNewsByTag(tag);
		List<News> newsList = new LinkedList<>();
		for(Document d : docList) {
			newsList.add(Utils.toNews(d));
		}
		return newsList;
	}


}
