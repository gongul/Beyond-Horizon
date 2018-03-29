package horizon.member.config;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

import horizon.member.data.Member;

public class MemberWriteConverter implements Converter<DBObject, Member>{
	Logger log = Logger.getLogger(this.getClass());
	
	
	@Override
	public Member convert(DBObject source) {
//		Member m = new Member();
		log.debug(source);
		Member m = new Member(source.get("_id").toString(),(String) source.get("id"));
		m.setName((String) source.get("name"));
		m.setPassword((String) source.get("password"));
		m.setSex((String) source.get("sex"));
		
		return m;
	}
	
	

	/*
	@Override
	public Member convert(Document source) {
//		Member m = new Member();
		Member m = new Member((String) source.get("ObjectId"),(String) source.get("id"));
		log.debug(source.get("ObjectId"));
		log.debug(source.get("id"));
//		m.setObjectId((String) source.get("ObjectId"));
//		m.setId((String) source.get("id"));
		m.setName((String) source.get("name"));
		m.setPassword((String) source.get("password"));
		m.setSex((String) source.get("sex"));
		
		return m;
	}
	*/

}
