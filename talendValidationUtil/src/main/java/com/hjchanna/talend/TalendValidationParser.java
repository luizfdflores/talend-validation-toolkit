package src.main.java.com.hjchanna.talend;

import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.java.com.hjchanna.talend.dto.ValidationRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonParser;

/**
 * @author ChannaJ
 */
public class TalendValidationParser {

    private static final Logger LOGGER = Logger.getLogger(TalendValidationParser.class);
    private static final TalendValidationParser INSTANCE = new TalendValidationParser();

    private TalendValidationParser() {
    }

    public static TalendValidationParser getInstance() {
        return INSTANCE;
    }

    /**
     * @return parse validation rules defined in resources and return as
     * ValidationRequest objects
     */
    public List<ValidationRequest> getValidationRequests() {
        List<ValidationRequest> validationRequests = new ArrayList<>();

        ObjectMapper jsonObjectMapper = new ObjectMapper();
        try {
            LOGGER.info("start to parse validation rules at " + Constraints.VALIDATION_FILE_PATH);

            //parsing validators.json
            InputStream validationRuleResource = getResourceAsStream(Constraints.VALIDATION_FILE_PATH);
            String[] validationRuleFileNames = jsonObjectMapper.readValue(validationRuleResource, new TypeReference<String[]>() {
            });

            //parse validators
            for (String validationRuleFileName : validationRuleFileNames) {
                LOGGER.info("parsing validation rule " + validationRuleFileName);
                InputStream resourceUri = getResourceAsStream(validationRuleFileName);
                ValidationRequest validationRequest = jsonObjectMapper.readValue(resourceUri, ValidationRequest.class);
                validationRequests.add(validationRequest);
            }

            LOGGER.info("finished parsing validation rules with rule count: " + validationRequests.size());
        } catch (IOException ex) {
            LOGGER.error("error parsing validation rules", ex);
        }

        return validationRequests;
    }

    private InputStream getResourceAsStream(String resource) {
        return getClass().getClassLoader().getResourceAsStream(resource);
    }
}
