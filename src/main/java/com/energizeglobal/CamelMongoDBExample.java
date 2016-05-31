package com.energizeglobal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mongodb.MongoDbConstants;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelMongoDBExample {

    public static void main(String[] args) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "camel-context-mongo.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
        camelContext.start();
        ProducerTemplate producer = camelContext.createProducerTemplate();

        camelContext.addRoutes(new CamelMongoRoute());

//        // route: from("direct:update").to("mongodb:myDb?database=science&collection=notableScientists&operation=update");
//        DBObject filterField = new BasicDBObject("filterField", true);
//        DBObject updateObj = new BasicDBObject("$set", new BasicDBObject("scientist", "Darwin"));
//        Object result = producer.requestBodyAndHeader("direct:update", new Object[]{filterField, updateObj}, MongoDbConstants.MULTIUPDATE, true);

        DBObject obj = new BasicDBObject();
        obj.put("firstname", "name");
        obj.put("lastname", "surname");
        obj.put("email", "email");

        DBObject result = producer.requestBody("direct:start", obj, DBObject.class);

        camelContext.stop();

    }
}