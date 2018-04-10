package horizon.board.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import horizon.board.data.domain.CommentResource;

public class CommentSerialize extends JsonSerializer<CommentResource>{

	@Override
	public void serialize(CommentResource value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeStartObject();
	}

}
