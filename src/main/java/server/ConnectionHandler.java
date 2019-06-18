package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import storage.ItemRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public class ConnectionHandler implements HttpHandler {

    /*
    Handle GET request, read Item name\size and send minimal ask
     */


    private static void parseQuery(String query, Map<String,
            Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String pairs[] = query.split("[&]");
            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0],
                            System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<String>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        Map<String, Object> parameters = new HashMap<>();
        URI requestedUri = httpExchange.getRequestURI();
        String query = requestedUri.getRawQuery();

        parseQuery(query, parameters);

        String response = "Minimal ask for the " + parameters.get("name") + " " + parameters.get("size") + " is:" + lookForAsk(parameters);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();

    }

    private Integer lookForAsk(Map<String, Object> parameters) {
        return ItemRepository.getAllItemsWithLowestAsks().stream()
                .filter(itemIntegerPair -> itemIntegerPair.getKey().getName().equals(parameters.get("name"))
                        && itemIntegerPair.getKey().getSize().equals(parameters.get("size")))
                .findFirst().orElseThrow(NoSuchElementException::new).getValue();
    }

}
