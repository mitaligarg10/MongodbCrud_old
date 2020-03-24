package com.stackroute;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Date;

public class MongodbDemo {
    public static void main(String[] args) {
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("testdb");
            DBCollection table = db.getCollection("user");

            //*** Insert ***
            BasicDBObject document = new BasicDBObject();
            document.put("name", "Aditi");
            document.put("age", 28);
            document.put("createdDate", new Date());
            table.insert(document);

            //*** Find and display 1 ***
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("age", 28);
            DBCursor cursor = table.find(searchQuery);
            while (cursor.hasNext()) {
                System.out.println("Inserted Document : " + cursor.next());
            }

            // *** Update ***
            BasicDBObject query = new BasicDBObject();
            query.put("age", 28);
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("age", 25);
            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);
            table.update(query, updateObj);

            // *** Find and display 2 ***
            BasicDBObject searchQuery2 = new BasicDBObject().append("age", 25);
            DBCursor cursor2 = table.find(searchQuery2);
            while (cursor2.hasNext()) {
                System.out.println("Updated Document : " + cursor2.next());
            }

            // *** Delete ***
            BasicDBObject searchQuery3 = new BasicDBObject();
            searchQuery.put("age", 28);
            table.remove(searchQuery3);

            /**** Done ****/
            System.out.println("Done");
        } catch (MongoException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
