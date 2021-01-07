package travelling.api.app.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import travelling.api.app.service.HttpLoggingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class HttpLoggingServiceImpl implements HttpLoggingService {

    private ObjectMapper objectMapper;

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);

        stringBuilder.append("REQUEST ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");

        stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");
        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        if (body != null) {
            stringBuilder.append("body=[").append(writeBodyToString(body)).append("]");
        }

        String logMess = stringBuilder.toString();
        log.info(logMess);
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        StringBuilder response = new StringBuilder("RESPONSE ")
                .append("method=[")
                .append(httpServletRequest.getMethod())
                .append("] ")
                .append("path=[")
                .append(httpServletRequest.getRequestURI())
                .append("] ")
                .append("responseHeaders=[")
                .append(buildHeadersMap(httpServletResponse))
                .append("] ")
                .append("responseBody=[")
                .append(writeBodyToString(body))
                .append("] ");

        log.info(response.toString());
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameters = httpServletRequest.getParameterNames();

        while (parameters.hasMoreElements()) {
            String key = parameters.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String key = (String) headers.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headers = response.getHeaderNames();
        headers.stream().forEach(header -> {
            map.put(header, response.getHeader(header));
        });

        return map;
    }

    private String writeBodyToString(Object body) {
        try {
            return objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }
}
