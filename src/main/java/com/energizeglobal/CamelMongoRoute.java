package com.energizeglobal;

import com.mongodb.DBObject;
import org.apache.camel.builder.RouteBuilder;

public class CamelMongoRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:start").convertBodyTo(DBObject.class)
                .to("mongodb:myDb?database=testBb&collection=users&operation=insert");

        from("direct:findAll")
                .to("mongodb:myDb?database=testBb&collection=users&operation=findAll")
                .to("mock:resultFindAll");

    }

}