package vttp2023.batch3.csf.assessment.cnserver.models;

import java.io.StringReader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class NewsInput {
    private String title;
    private MultipartFile photo;
    private String description;
    private List<String> tags;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public MultipartFile getPhoto() {
        return photo;
    }
    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
    @Override
    public String toString() {
        return "NewsInput [title=" + title + ", photo=" + photo + ", description=" + description + ", tags=" + tags
                + "]";
    }

    // public static NewsInput getFromJson(String jsonString) {
    //     JsonObject o = readJSON(jsonString);
    //     NewsInput n = new NewsInput();
    //     n.setTitle(o.getString("title"));
    //     n.setDescription(o.getString("description"));
    //     // n.setTags(o.getJsonArray("tags");
    //     // System.out.println("in model getfromjson>>>>>"+m);
    //     return (n);
    // }

    // public static JsonObject readJSON(String json){
	// 		JsonReader r = Json.createReader(new StringReader(json));
	// 		return r.readObject();
	// 	}
    

    
}
