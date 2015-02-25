package com.mongodb;

import org.junit.Test;

import java.net.UnknownHostException;

/**
 * Created by wanglei on 15-2-25.
 */
public class TestMongo {

    //find all
    public void findAll(DBCollection user){
        DBCursor cursor=user.find();
        while (cursor.hasNext()){
            System.out.println("user content:"+cursor.next());
        }
    }

    //add a data
    public void add(DBCollection collection,DBObject map){
        //add a data
        collection.save(map);
    }

    //del a data
    public void del(DBCollection dbCollection,DBObject map){
        dbCollection.remove(map);
    }

    //update
    public void update(DBCollection collection,DBObject preObj,DBObject newObj){
        collection.update(preObj,newObj);
    }

    @Test
    public void testMongo(){
        try {
            MongoClient client=new MongoClient("localhost",27017);
            for (String database:client.getDatabaseNames()){
                System.out.println(database);
            }

            DB db=client.getDB("test");
            for (String name:db.getCollectionNames()){
                System.out.println("collection name:"+name);
            }
            DBCollection user=db.getCollection("user");

            //find single data
            System.out.println("name is liu:"+user.find(new BasicDBObject("name","liu")).next().get("name"));

            update(user,user.find(new BasicDBObject("name","liu")).next(),new BasicDBObject("name","zzzzz"));

            findAll(user);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
