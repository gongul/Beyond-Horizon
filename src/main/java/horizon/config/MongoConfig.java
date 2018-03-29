package horizon.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import horizon.member.config.MemberWriteConverter;

@Configuration
@EnableMongoRepositories(basePackages="horizon")
public class MongoConfig extends AbstractMongoConfiguration{

	@Override
	protected String getDatabaseName() {
		return "horizon";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		String mongoUri = ";
		MongoClientURI connStr = new MongoClientURI(mongoUri);
		MongoClient mongoClient = new MongoClient(connStr);
		
		return mongoClient;
	}
	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
	    return new SimpleMongoDbFactory(mongo(), getDatabaseName());
	}
	
	@Override
	protected String getMappingBasePackage() {
		// TODO Auto-generated method stub
		return "horizon";
	}
	
	@Bean
	public CustomConversions customConversions() {
	    List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
	    converters.add(new MemberWriteConverter());
	    return new CustomConversions(converters);
	}
	
	@Bean
	public MappingMongoConverter mongoConverter() throws Exception {
	    MongoMappingContext mappingContext = new MongoMappingContext();
	    DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
	    MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
	    mongoConverter.setCustomConversions(customConversions());
	    return mongoConverter;
	}
	
	@Override
	public @Bean MongoTemplate mongoTemplate() throws Exception {
		// TODO Auto-generated method stub
		return new MongoTemplate(mongoDbFactory(), mongoConverter());
	}



}
