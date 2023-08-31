package vttp2023.batch3.csf.assessment.cnserver.Utils;

import org.bson.Document;

import vttp2023.batch3.csf.assessment.cnserver.models.News;

public class Utils {
    public static News toNews(Document doc) {
		return new News(doc.getString("c_id"), doc.getLong("postDate"), doc.getString("title"), doc.getString("description"), doc.getString("image"), doc.getList("tags", String.class));
	}
}
