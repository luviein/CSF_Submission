package vttp2023.batch3.csf.assessment.cnserver.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.mongodb.MongoWriteException;

import vttp2023.batch3.csf.assessment.cnserver.models.News;

@Repository
public class NewsRepository {

	// TODO: Task 1 
	// Write the native Mongo query in the comment above the method
	 @Autowired
    private MongoTemplate template;

    public boolean postComment(News news) {

        try{
            String cid = UUID.randomUUID().toString().substring(0, 8);
            Document doc = new Document();
            doc.put("c_id", cid);

            doc.put("postDate", news.getPostDate());
            doc.put("title", news.getTitle());
            doc.put("description", news.getDescription());
            doc.put("image", news.getImage());
			List<String> newTagList = new ArrayList<>();
			for(String tag: news.getTags()){
				tag.replace("\\", "").replace("\\\\", "");
				newTagList.add(tag);
			}
			doc.put("tags", newTagList);

            template.insert(doc, "info");

            return true;
        } catch (MongoWriteException e) {
            e.printStackTrace();
            return false;
        }
        

    }



	// TODO: Task 2 
	// Write the native Mongo query in the comment above the method

	// GET POSTED TIME FROM MONGO

	// db.getCollection("info").aggregate([
 	// {"$project": {"tags": 1, "postDate":1,"_id":0} }
	// ])

	// public List<Document> getTagAndTime(Integer time) {
	// 	ProjectionOperation projection = Aggregation.project("tags", "postDate").andExclude("_id");
    //     Aggregation pipeline = Aggregation.newAggregation(projection);
    //     return template.aggregate(pipeline, "info", Document.class).getMappedResults();
	// }

	
// 	db.info.aggregate(
//   [
//   {$unwind: "$tags"},
//   {$group:{_id: "$tags", count: {$sum:1}}}, {$sort: {count:-1, _id:1}}, {$limit:10}
      

    
//   ]
// )

	public List<Document> getTagAndCount() {
		UnwindOperation unwind = Aggregation.unwind("tags");
		GroupOperation group = Aggregation.group("tags")
			.push("_id").as("tags")
			.count().as("count");
			
		SortOperation sort1 = Aggregation.sort(Sort.by(Direction.DESC, "count"));
		SortOperation sort2 = Aggregation.sort(Sort.by(Direction.ASC, "_id"));
		Aggregation pipeline = Aggregation.newAggregation(unwind, group, sort1, sort2);
		return template.aggregate(pipeline, "info", Document.class).getMappedResults();
	}

	
	// TODO: Task 3
	// Write the native Mongo query in the comment above the method

	//db.info.aggregate(
//   [
//   {$unwind: "$tags"},    
//   ]
// )
	public List<Document> getNewsByTag(@PathVariable String tag) {
		UnwindOperation unwind = Aggregation.unwind("tags");
		Aggregation pipeline = Aggregation.newAggregation(unwind);
		return template.aggregate(pipeline, "info", Document.class).getMappedResults();
	}

}
