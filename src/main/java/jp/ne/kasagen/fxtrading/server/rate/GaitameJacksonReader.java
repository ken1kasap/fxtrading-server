package jp.ne.kasagen.fxtrading.server.rate;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author kasagen
 */
@Provider
@Consumes(MediaType.TEXT_HTML)
public class GaitameJacksonReader implements MessageBodyReader<Rates> {
    
    @Override
    public boolean isReadable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return type == Rates.class;
    }

    @Override
    public Rates readFrom(Class<Rates> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException, WebApplicationException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(entityStream, Rates.class);
    }    
}
